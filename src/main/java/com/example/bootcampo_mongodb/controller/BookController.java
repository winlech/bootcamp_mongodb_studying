package com.example.bootcampo_mongodb.controller;

import com.example.bootcampo_mongodb.entities.Book;
import com.example.bootcampo_mongodb.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book bookResponse = bookRepository.save(book);
        return new ResponseEntity<>(bookResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAll() {
        return new ResponseEntity<>(bookRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable String id) {
        Book book = bookRepository.findById(id).orElse(new Book());
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/author")
    public ResponseEntity<List<Book>> getByAuthor(@RequestParam(required = true, name = "author") String author) {
        List<Book> books = bookRepository.findBooksByAuthor(author).orElse(new ArrayList<>());
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteBook(@PathVariable String id) {
        bookRepository.deleteById(id);
        return HttpStatus.OK;
    }

}
