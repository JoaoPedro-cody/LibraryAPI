package io.silva.bookshop.repository;

import io.silva.bookshop.model.Book;
import io.silva.bookshop.model.Loan;
import io.silva.bookshop.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@SpringBootTest
public class LoanRepositoryTest {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    LoanRepository loanRepository;

    @Test
    void save(){
        User user = userRepository.findById(UUID.fromString("ffb05b73-9017-4738-9c11-710d48d46bd9")).orElse(null);
        Book book = bookRepository.findById(UUID.fromString("55f5490e-7f64-4873-a688-ce33e03117c0")).orElse(null);
        LocalDate date = LocalDate.now();
        Loan loan = new Loan();

        loan.setUser(user);
        loan.setBook(book);
        loan.setLoanDate(date);
        loan.setReturnDate(date.plusDays(7));

        loanRepository.save(loan);
    }

    @Test
    void searchLoan(){
        Loan loan = loanRepository.findById(UUID.fromString("0be42d01-9022-4b0d-9524-db7f8da4a1d1")).orElse(null);
        System.out.println(
                "Loan" +
                "\nUser: " + loan.getUser().getName() +
                "\nBook: " + loan.getBook().getTitle() +
                "\nLoanDate: " + loan.getLoanDate() +
                "\nReturnDate: " + loan.getReturnDate() +
                "\nLoan Id: " + loan.getUuid()
        );
    }



}
