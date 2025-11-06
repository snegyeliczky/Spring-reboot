package com.amigoscode.book;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    private final BookRepository bookRepository;

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public void insertBook(List<Book> bookList) {
        if (bookList == null || bookList.isEmpty()) {
            return;
        }
        bookRepository.saveAll(bookList);
    }

}
