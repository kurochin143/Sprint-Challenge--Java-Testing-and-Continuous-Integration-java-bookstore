package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<?> getBooks() {
        ArrayList<Book> books = bookService.findAll();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PutMapping("/data/books/{id}")
    public ResponseEntity<?> updateBook(@PathVariable("id") long id, @RequestBody Book book) {
        bookService.update(id, book);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @PostMapping("/data/books/authors/{id}")
//    public ResponseEntity<?> updateBookAuthor()

    @DeleteMapping("data/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") long id) {
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
