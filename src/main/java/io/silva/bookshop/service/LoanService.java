package io.silva.bookshop.service;

import io.silva.bookshop.NotFoundException;
import io.silva.bookshop.dto.CreateLoanRequest;
import io.silva.bookshop.model.Book;
import io.silva.bookshop.model.Loan;
import io.silva.bookshop.model.User;
import io.silva.bookshop.repository.BookRepository;
import io.silva.bookshop.repository.LoanRepository;
import io.silva.bookshop.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class LoanService {
    LoanRepository loanRepository;
    BookRepository bookRepository;
    UserRepository userRepository;

    public void saveLoan(CreateLoanRequest c){
        Loan loan = new Loan();
        Book book = bookRepository.findById(c.bookId()).orElseThrow(() -> new RuntimeException("Book Not Found"));
        if (book.getLoan() == true){
            throw new RuntimeException("This book has already been borrowed!");
        }else {
            book.setLoan(true);

            LocalDate localDate = LocalDate.now();
            loan.setLoanDate(localDate);
            loan.setReturnDate(localDate.plusDays(7));
            loan.setBook(book);
            loan.setUser(userRepository.findById(c.userId()).orElseThrow(() -> new RuntimeException("User not Found")));

            loanRepository.save(loan);
        }
    }

    public Loan searchLoan(UUID loanId){
        return loanRepository.findById(loanId).orElseThrow(() -> new NotFoundException("Loan not Found"));
    }

    public List<Loan> listLoans(UUID bookId, UUID userId){
        if (bookId != null){
            return loanRepository.findByBook(bookRepository.findById(bookId).orElseThrow(() -> new NotFoundException("Book not found")));
        } else if (userId != null) {
            return loanRepository.findByUser(userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found")));
        } else {
            List<Loan> all = loanRepository.findAll();
            if (all.isEmpty()){
                throw new RuntimeException("No loans registered");
            } else {
                return all;
            }
        }
    }

    public void updateLoan(CreateLoanRequest createLoanRequest, UUID id){
        Loan loan = loanRepository.findById(id).orElseThrow(() -> new NotFoundException("Loan not found!"));
        User user = userRepository.findById(createLoanRequest.userId()).orElse(null);
        Book book = bookRepository.findById(createLoanRequest.bookId()).orElse(null);

        if (user != null){
            loan.setUser(user);
        }
        if (book != null){
            loan.setBook(book);
        }

        loanRepository.save(loan);

    }

    public void deleteLoan(UUID loanId){
        Loan loan = searchLoan(loanId);
        loan.getBook().setLoan(false);
        loanRepository.deleteById(loanId);
    }
}
