package io.silva.bookshop.repository;

import io.silva.bookshop.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;

    @Test
    void saveTest(){
        Book book = new Book();

        book.setTitle("Harry Potter and the Philosopher's Stone");
        book.setLoan(true);
        book.setAuthor("J. K. Rowling");
        book.setPublicationDate(LocalDate.of(1997, 6, 26));

        bookRepository.save(book);
    }
}
