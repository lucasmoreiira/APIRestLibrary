package com.example.crud.controller;


import com.example.crud.domain.Book.BookRepository;
import com.example.crud.service.BookService;
import com.example.crud.domain.Book.RequestBookDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository repository;


    @GetMapping("/{id}")
    public ResponseEntity getBookById(@PathVariable String id){
        return bookService.getBookById(id);
    }

    @GetMapping
    public ResponseEntity getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping
    public ResponseEntity registerBooks(@RequestBody @Valid RequestBookDTO data){
        return bookService.registerBooks(data);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateBook(@RequestBody @Valid RequestBookDTO data){
            return bookService.updateBook(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteBook(@PathVariable @Valid String id ){
        return bookService.deleteBook(id);
    }



}
