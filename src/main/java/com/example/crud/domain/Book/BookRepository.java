package com.example.crud.domain.Book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {
       List<Book> findAllByActiveTrue();
        List<Book> findBookById(String id);
}
