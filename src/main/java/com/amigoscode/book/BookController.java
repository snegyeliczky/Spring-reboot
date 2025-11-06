package com.amigoscode.book;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    private final BookService bookService;



    @RequestMapping
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @PostMapping("/insertBooks")
    public void insertBooks(@RequestBody List<Book> books) {
        bookService.insertBook(books);
    }
}
