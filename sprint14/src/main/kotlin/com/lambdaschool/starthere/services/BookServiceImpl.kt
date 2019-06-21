package com.lambdaschool.starthere.services

import com.lambdaschool.starthere.exceptions.ResourceNotFoundException
import com.lambdaschool.starthere.models.Author
import com.lambdaschool.starthere.models.Book
import com.lambdaschool.starthere.repository.AuthorRepository
import com.lambdaschool.starthere.repository.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

import java.util.ArrayList
import java.util.function.Consumer

@Service(value = "bookService")
open class BookServiceImpl : BookService {

    @Autowired
    internal var bookRepository: BookRepository? = null

    @Autowired
    internal var authorRepository: AuthorRepository? = null

    override fun findAll(pageable: Pageable): ArrayList<Book> {
        val outBooks = ArrayList<Book>()
        bookRepository!!.findAll(pageable).iterator().forEachRemaining(Consumer<Book> { outBooks.add(it) })
        return outBooks
    }

    override fun update(id: Long, book: Book) {
        val currentBook = bookRepository!!.findById(id)
                .orElseThrow { ResourceNotFoundException("Cannot find book id$id") }

        if (book.booktitle != null) {
            currentBook.booktitle = book.booktitle
        }

        if (book.isbn != null) {
            currentBook.isbn = book.isbn
        }

        currentBook.copy = book.copy

        if (book.authors.isNotEmpty()) {
            val newAuthors: MutableList<Author> = mutableListOf()
            for (author in book.authors) {
                newAuthors.add(Author(author.lastname, author.firstname))
            }
            currentBook.authors = newAuthors
        }

        bookRepository!!.save(currentBook)
    }

    override fun delete(id: Long) {
        val currentBook = bookRepository!!.findById(id)
                .orElseThrow { ResourceNotFoundException("Cannot find book id$id") }

        if (currentBook != null) {
            bookRepository!!.deleteById(id)
        }
    }

    override fun setBookAuthor(bookid: Long, authorid: Long) {
        val book = bookRepository!!.findById(bookid)
                .orElseThrow { ResourceNotFoundException("Cannot find book id$bookid") }

        val author = authorRepository!!.findById(authorid)
                .orElseThrow { ResourceNotFoundException("Cannot find author id$authorid") }

        if (book != null || author != null) {

            (book!!.authors as MutableList).add(author)
        }

        bookRepository!!.save(book)
    }
}
