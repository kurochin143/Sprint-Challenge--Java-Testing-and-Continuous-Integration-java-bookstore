package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.exceptions.ResourceNotFoundException;
import com.lambdaschool.starthere.models.Author;
import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service(value = "bookService")
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public ArrayList<Book> findAll() {
        ArrayList<Book> outBooks = new ArrayList<>();
        bookRepository.findAll().iterator().forEachRemaining(outBooks::add);
        return outBooks;
    }

    @Override
    public void update(long id, Book book) {
        Book currentBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find book id" + id));

        if (book.getBooktitle() != null) {
            currentBook.setBooktitle(book.getBooktitle());
        }

        if (book.getIsbn() != null) {
            currentBook.setIsbn(book.getIsbn());
        }

        currentBook.setCopy(book.getCopy());

        if (book.getAuthors().size() != 0) {
            currentBook.getAuthors().clear();
            ArrayList<Author> newAuthors = new ArrayList<>();
            for (Author author : book.getAuthors()) {
                newAuthors.add(new Author(author.getLastname(), author.getFirstname()));
            }
            currentBook.setAuthors(newAuthors);
        }

        bookRepository.save(currentBook);
    }

    @Override
    public void delete(long id) {
        Book currentBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find book id" + id));

        if (currentBook != null) {
            bookRepository.deleteById(id);
        }
    }
}
