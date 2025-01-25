package com.example.crud.service;

import com.example.crud.domain.Book;
import com.example.crud.domain.BookRepository;
import com.example.crud.domain.RequestBookDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;


    public ResponseEntity getBookById(@PathVariable String id) {
        List<Book> book = repository.findBookById(id);
        if(book.isEmpty()){
            throw new EntityNotFoundException("data not found with provided ID");

        }
        return ResponseEntity.ok(book.get(0));
    }


    public ResponseEntity getAllBooks() {
        List<Book>allBooks = repository.findAllByActiveTrue();
        if(allBooks.isEmpty()){
            throw new EntityNotFoundException();
        }
        return ResponseEntity.ok(allBooks);
    }


    public ResponseEntity registerBooks(@RequestBody @Valid RequestBookDTO data) {
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

