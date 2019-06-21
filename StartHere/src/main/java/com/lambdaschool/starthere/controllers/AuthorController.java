package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.models.Author;
import com.lambdaschool.starthere.services.AuthorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @ApiOperation(value = "return all authors", response = Author.class, responseContainer = "List")
    @GetMapping("/authors")
    public ResponseEntity<?> getAuthors() {
        ArrayList<Author> authors = authorService.findAll();
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    

}
