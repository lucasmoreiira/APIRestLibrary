package com.example.crud.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book , String> {
       List<Book> findAllByActiveTrue();
        List<Book> findBookById(String id);
}
