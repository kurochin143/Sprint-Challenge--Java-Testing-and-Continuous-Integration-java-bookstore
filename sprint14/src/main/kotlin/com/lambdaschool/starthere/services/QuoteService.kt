package com.lambdaschool.starthere.services

import com.lambdaschool.starthere.models.Quote

interface QuoteService {
    fun findAll(): List<Quote>

    fun findQuoteById(id: Long): Quote

    fun findByUserName(username: String): List<Quote>

    fun delete(id: Long)

    fun save(quote: Quote): Quote
}
