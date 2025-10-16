package io.silva.bookshop.service;

import io.silva.bookshop.model.Book;
import io.silva.bookshop.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class BookService {
    BookRepository bookRepository;

    public void saveBook(Book b){
        b.setLoan(false);
        bookRepository.save(b);
    }

    public List<Book> listAll(){
        return bookRepository.findAllBooksSorted();
    }

    public List<Book> listBookByTitle(String title){
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Book> listByAuthor(String author){
        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }

    public void updateBook(String uuid, Book b){
        Book byId = bookRepository.findById(UUID.fromString(uuid)).orElse(null);
        byId.setTitle(b.getTitle());
        byId.setAuthor(b.getAuthor());
        byId.setPublicationDate(b.getPublicationDate());
        bookRepository.save(byId);
    }

    public void deleteBook(String uuid){
        bookRepository.deleteById(UUID.fromString(uuid));
    }
}
