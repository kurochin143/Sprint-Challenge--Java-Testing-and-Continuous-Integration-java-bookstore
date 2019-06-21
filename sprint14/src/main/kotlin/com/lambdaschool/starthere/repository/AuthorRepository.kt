package com.lambdaschool.starthere.repository

import com.lambdaschool.starthere.models.Author
import org.springframework.data.repository.PagingAndSortingRepository

interface AuthorRepository : PagingAndSortingRepository<Author, Long>
