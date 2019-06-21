package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Author;
import com.lambdaschool.starthere.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service(value = "authorService")
public class AuthorServiceImpl implements AuthorService{

    @Autowired
    AuthorRepository authorRepository;

    @Override
    public ArrayList<Author> findAll() {
        ArrayList<Author> outAuthors = new ArrayList<>();
        authorRepository.findAll().iterator().forEachRemaining(outAuthors::add);
        return outAuthors;
    }
}
