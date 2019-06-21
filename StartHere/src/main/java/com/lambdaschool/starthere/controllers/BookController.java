package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.services.BookService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @ApiOperation(value = "return all books", response = Book.class, responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})
    @GetMapping("/books")
    public ResponseEntity<?> getBooks(@PageableDefault(page = 0, size = 5) Pageable pageable) {
        ArrayList<Book> books = bookService.findAll(pageable);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_DATA')")
    @ApiOperation(value = "update book data")
    @PutMapping("/data/books/{id}")
    public ResponseEntity<?> updateBook(@PathVariable("id") long id, @RequestBody Book book) {
        bookService.update(id, book);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_DATA')")
    @ApiOperation(value = "add an existing author to an existing book")
    @PostMapping("/data/books/authors/{bookid}/{authorid}")
    public ResponseEntity<?> setBookAuthor(@PathVariable("bookid") long bookid, @PathVariable("authorid") long authorid) {

        bookService.setBookAuthor(bookid, authorid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_DATA')")
    @ApiOperation(value = "delete a book")
    @DeleteMapping("data/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") long id) {
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
