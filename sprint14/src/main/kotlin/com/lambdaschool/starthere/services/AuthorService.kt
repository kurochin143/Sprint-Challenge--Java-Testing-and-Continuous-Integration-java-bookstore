package com.lambdaschool.starthere.services

import com.lambdaschool.starthere.models.Author
import java.util.ArrayList

interface AuthorService {

    fun findAll(): ArrayList<Author>

}
