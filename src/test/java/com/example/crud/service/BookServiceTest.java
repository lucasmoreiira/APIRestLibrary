package com.example.crud.service;

import com.example.crud.domain.Book;
import com.example.crud.domain.BookRepository;
import com.example.crud.domain.RequestBookDTO;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should get the book successly")
    public void GetBookWithTheProvidedIdSuccess() throws Exception {

        //Given
        String id = UUID.randomUUID().toString();
        Book book = new Book(new RequestBookDTO(
                id,
                "The Great Gatsby",
                "F. Scott Fitzgerald",
                "Fiction",
                "9780743273565"
        ));

        List<Book> listBook = new ArrayList<>();
        listBook.add(book);

        //When
        when(bookRepository.findBookById(id)).thenReturn(listBook);

        //Then
        ResponseEntity<Book> expected = ResponseEntity.ok(book);
        ResponseEntity<Book> response = bookService.getBookById(id);

        //Assert
        assertEquals(expected.getStatusCode(), response.getStatusCode());
        assertEquals(expected.getBody(), response.getBody());


    }

    @Test
    @DisplayName("Should not get the book successly")
    public void GetBookWithTheProvidedIdFailure() throws Exception {
        //Given
        String id = "";
        List<Book> emptyList = new ArrayList<>();

        //When
        when(bookRepository.findBookById(id)).thenReturn(emptyList);

        //Then
        String expected = "data not found with provided ID";
        EntityNotFoundException response = assertThrows(EntityNotFoundException.class, () -> {
            bookService.getBookById(id);
        });

        //Assert
        assertEquals(expected, response.getMessage());
    }


    }




