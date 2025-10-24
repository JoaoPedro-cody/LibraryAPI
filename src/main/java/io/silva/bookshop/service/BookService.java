package io.silva.bookshop.service;

import io.silva.bookshop.NotFoundException;
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
        Book byTitle = bookRepository.findByTitle(b.getTitle());
        if (byTitle == null){
            b.setLoan(false);
            bookRepository.save(b);
        }else {
            throw new RuntimeException("Book already registered!");
        }
    }

    public Book searchBook(UUID uuid){
        return bookRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Book not found!"));
    }

    public List<Book> listAll(String title, String author){
        List<Book> allBooksSorted = bookRepository.findAllBooksSorted();

        if (title != null){
            List<Book> byTitleContainingIgnoreCase = bookRepository.findByTitleContainingIgnoreCase(title);
            if (byTitleContainingIgnoreCase.isEmpty()){
                throw new RuntimeException("No books found!");
            }else {
                return byTitleContainingIgnoreCase;
            }
        } else if (author != null) {
            List<Book> byAuthorContainingIgnoreCase = bookRepository.findByAuthorContainingIgnoreCase(author);
            if (byAuthorContainingIgnoreCase.isEmpty()){
                throw new RuntimeException("No books found!");
            } else {
                return byAuthorContainingIgnoreCase;
            }
        } else {
            if (allBooksSorted.isEmpty()){
                throw new RuntimeException("no books registered!");
            }else {
                return allBooksSorted;
            }
        }

    }

    public void updateBook(UUID uuid, Book b){
        Book byId = searchBook(uuid);
        byId.setTitle(b.getTitle());
        byId.setAuthor(b.getAuthor());
        byId.setPublicationDate(b.getPublicationDate());
        bookRepository.save(byId);
    }

    public void deleteBook(UUID uuid){
        searchBook(uuid);
        bookRepository.deleteById(uuid);
    }
}
