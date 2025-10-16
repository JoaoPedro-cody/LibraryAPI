package io.silva.bookshop.repository;

import io.silva.bookshop.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface BookRepository  extends JpaRepository<Book, UUID> {
    @Query("""
        select b from Book b order by b.title asc
    """)
    List<Book> findAllBooksSorted();
    List<Book> findByAuthorContainingIgnoreCase(String author);
    List<Book> findByTitleContainingIgnoreCase(String title);

}
