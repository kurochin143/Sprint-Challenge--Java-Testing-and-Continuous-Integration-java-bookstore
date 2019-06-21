package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Book;

import java.util.ArrayList;

public interface BookService {

    ArrayList<Book> findAll();
    void update(long id, Book book);
    void delete(long id);

}
