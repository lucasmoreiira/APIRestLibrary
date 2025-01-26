package com.example.crud.service;

import com.example.crud.domain.Book.Book;
import com.example.crud.domain.Book.BookRepository;
import com.example.crud.domain.Book.RequestBookDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public ResponseEntity getBookById(@PathVariable String id) {
        var book = repository.findBookById(id);
        return ResponseEntity.ok(book);
    }


    public ResponseEntity getAllBooks() {
        var allBooks = repository.findAllByActiveTrue();
        return ResponseEntity.ok(allBooks);
    }


    public ResponseEntity<Book> registerBooks(@RequestBody RequestBookDTO data) {
        Book newBook = new Book(data);
        repository.save(newBook);
        return ResponseEntity.ok().build();
    }



    public ResponseEntity updateBook(@RequestBody @Valid RequestBookDTO data) {
        Optional<Book> optionalBook = repository.findById(data.id());
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setName(data.name());
            book.setAuthor(data.author());
            book.setGenre(data.genre());
            book.setISBN(data.ISBN());
            return ResponseEntity.ok(book);
        } else {
            throw new EntityNotFoundException();
        }
    }


    public ResponseEntity deleteBook(@PathVariable @Valid String id) {
        Optional<Book> optionalBook = repository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.deactivate();
            return ResponseEntity.noContent().build();

        } else {
            throw new EntityNotFoundException();
        }
    }
}

