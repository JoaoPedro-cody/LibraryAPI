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

    @PostMapping
    public void saveBook(@RequestBody Book book){
        bookService.saveBook(book);
    }

    @GetMapping("{id}")
    public Book searchBook(@PathVariable UUID id){
        return bookService.searchBook(id);
    }

    @GetMapping
    public List<Book> searchBooks(@RequestParam(required = false) String title, @RequestParam(required = false) String author){
        return bookService.listAll(title, author);
    }

    @PutMapping("{id}")
    public void updateBook(@PathVariable UUID id, @RequestBody Book b){
        bookService.updateBook(id, b);
    }

    @DeleteMapping("{id}")
    public void  deleteBook(@PathVariable UUID id){
        bookService.deleteBook(id);
    }
}
