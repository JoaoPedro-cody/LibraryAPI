package io.silva.bookshop.service;

import io.silva.bookshop.NotFoundException;
import io.silva.bookshop.model.Book;
import io.silva.bookshop.model.Loan;
import io.silva.bookshop.repository.BookRepository;
import io.silva.bookshop.repository.LoanRepository;
import io.silva.bookshop.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class LoanService {
    LoanRepository loanRepository;
    BookRepository bookRepository;
    UserRepository userRepository;

    public void saveLoan(UUID bookId, UUID userId){
        Loan loan = new Loan();
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book Not Found"));
        if (book.getLoan() == true){
            throw new RuntimeException("This book has already been borrowed!");
        }else {
            book.setLoan(true);

            LocalDate localDate = LocalDate.now();
            loan.setLoanDate(localDate);
            loan.setReturnDate(localDate.plusDays(7));
            loan.setBook(book);
            loan.setUser(userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not Found")));

            loanRepository.save(loan);
        }
    }

    public void deleteLoan(UUID loanId){
        searchLoan(loanId);
        loanRepository.deleteById(loanId);
    }

    public Loan searchLoan(UUID loanId){
        return loanRepository.findById(loanId).orElseThrow(() -> new NotFoundException("Loan not Found"));
    }

    public List<Loan> listLoans(){
        List<Loan> all = loanRepository.findAll();
        if (all.isEmpty()){
            throw new RuntimeException("No loans registered");
        } else {
            return all;
        }
    }

    public Loan listBookLoans(UUID bookId){
        return loanRepository.findByBook(bookRepository.findById(bookId).orElseThrow(() -> new NotFoundException("Book not found")));
    }

    public List<Loan> listUserLoans(UUID userId){
        List<Loan> userLoans = loanRepository.findByUser(userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found")));
        if (userLoans.isEmpty()){
            throw new RuntimeException("This user did not borrow any books!");
        } else {
            return userLoans;
        }
    }
}
