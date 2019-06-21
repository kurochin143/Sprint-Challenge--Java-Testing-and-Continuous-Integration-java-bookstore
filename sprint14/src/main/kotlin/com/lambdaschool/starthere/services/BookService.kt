package com.lambdaschool.starthere.services

import com.lambdaschool.starthere.models.Book
import org.springframework.data.domain.Pageable

import java.util.ArrayList

interface BookService {

    fun findAll(pageable: Pageable): ArrayList<Book>
    fun update(id: Long, book: Book)
    fun delete(id: Long)
    fun setBookAuthor(bookid: Long, authorid: Long)

}
