package io.silva.bookshop.repository;

import io.silva.bookshop.model.Book;
import io.silva.bookshop.model.Loan;
import io.silva.bookshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LoanRepository extends JpaRepository<Loan, UUID> {
    List<Loan> findByBook(Book b);
    List<Loan> findByUser(User user);
}
