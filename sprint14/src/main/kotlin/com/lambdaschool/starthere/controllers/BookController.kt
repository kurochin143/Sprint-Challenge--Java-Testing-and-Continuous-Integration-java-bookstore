package com.lambdaschool.starthere.controllers

import com.lambdaschool.starthere.models.Book
import com.lambdaschool.starthere.services.BookService
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

import java.util.ArrayList

@RestController
class BookController {

    @Autowired
    internal var bookService: BookService? = null

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @ApiOperation(value = "return all books", response = Book::class, responseContainer = "List")
    @ApiImplicitParams(ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N)"), ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page."), ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). " +
            "Default sort order is ascending. " +
            "Multiple sort criteria are supported."))
    @GetMapping("/books")
    fun getBooks(@PageableDefault(page = 0, size = 5) pageable: Pageable): ResponseEntity<*> {
        val books = bookService!!.findAll(pageable)
        return ResponseEntity(books, HttpStatus.OK)
    }

    @PreAuthorize("hasAuthority('ROLE_DATA')")
    @ApiOperation(value = "update book data")
    @PutMapping("/data/books/{id}")
    fun updateBook(@PathVariable("id") id: Long, @RequestBody book: Book): ResponseEntity<*> {
        bookService!!.update(id, book)
        return ResponseEntity<Any>(HttpStatus.OK)
    }

    @PreAuthorize("hasAuthority('ROLE_DATA')")
    @ApiOperation(value = "add an existing author to an existing book")
    @PostMapping("/data/books/authors/{bookid}/{authorid}")
    fun setBookAuthor(@PathVariable("bookid") bookid: Long, @PathVariable("authorid") authorid: Long): ResponseEntity<*> {

        bookService!!.setBookAuthor(bookid, authorid)
        return ResponseEntity<Any>(HttpStatus.OK)
    }

    @PreAuthorize("hasAuthority('ROLE_DATA')")
    @ApiOperation(value = "delete a book")
    @DeleteMapping("data/books/{id}")
    fun deleteBook(@PathVariable("id") id: Long): ResponseEntity<*> {
        bookService!!.delete(id)
        return ResponseEntity<Any>(HttpStatus.OK)
    }


}
