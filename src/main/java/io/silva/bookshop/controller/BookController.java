package io.silva.bookshop.controller;

import io.silva.bookshop.model.Book;
import io.silva.bookshop.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("books")
@AllArgsConstructor
public class BookController {
    private BookService bookService;

    @PostMapping("/save")
    public void saveBook(@RequestBody Book book){
        bookService.saveBook(book);
    }

    @GetMapping("/all-books")
    public List<Book> searchBook(){
        return bookService.listAll();
    }

    @GetMapping("/by-title/{title}")
    public List<Book> searchBookByTitle(@PathVariable String title){
        return bookService.listBookByTitle(title);
    }

    @GetMapping("/by-author/{author}")
    public List<Book> searchByAuthor(@PathVariable String author){
        return bookService.listByAuthor(author);
    }

    @PutMapping("/update/{id}")
    public void updateBook(@PathVariable UUID id, @RequestBody Book b){
        bookService.updateBook(id, b);
    }

    @DeleteMapping("/delete/{id}")
    public void  deleteBook(@PathVariable UUID id){
        bookService.deleteBook(id);
    }
}
