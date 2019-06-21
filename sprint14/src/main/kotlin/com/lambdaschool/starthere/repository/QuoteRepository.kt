package com.lambdaschool.starthere.repository

import com.lambdaschool.starthere.models.Quote
import org.springframework.data.repository.CrudRepository

interface QuoteRepository : CrudRepository<Quote, Long>
