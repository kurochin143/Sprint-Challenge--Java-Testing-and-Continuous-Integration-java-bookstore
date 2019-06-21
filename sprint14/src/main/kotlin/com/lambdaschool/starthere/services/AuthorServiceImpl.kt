package com.lambdaschool.starthere.services

import com.lambdaschool.starthere.models.Author
import com.lambdaschool.starthere.repository.AuthorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.util.ArrayList
import java.util.function.Consumer

@Service(value = "authorService")
open class AuthorServiceImpl : AuthorService {

    @Autowired
    internal var authorRepository: AuthorRepository? = null

    override fun findAll(): ArrayList<Author> {
        val outAuthors = ArrayList<Author>()
        authorRepository!!.findAll().iterator().forEachRemaining(Consumer<Author> { outAuthors.add(it) })
        return outAuthors
    }
}
