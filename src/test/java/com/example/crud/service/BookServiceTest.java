package com.example.crud.service;

import com.example.crud.domain.Book.Book;
import com.example.crud.domain.Book.RequestBookDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private BookService bookService;

    @Test
    @DisplayName("Should create the book successly")
    void createBookSuccess() throws  Exception{


        Book book = new Book(new RequestBookDTO(
                UUID.randomUUID().toString(),
                "The Great Gatsby",
                "F. Scott Fitzgerald",
                "Fiction",
                "9780743273565"
        ));

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk());
    }


    @Test
    @DisplayName("Should fail to create the book")
    void createBookFailure() throws Exception{

        Book invalidBook = new Book(new RequestBookDTO(null,null,null,null,null));

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidBook)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should delete the book with the provided Id from DB")
    void deleteBookSuccess() throws Exception{

        String bookId = UUID.randomUUID().toString();

        when(bookService.deleteBook(bookId)).thenReturn(ResponseEntity.noContent().build());

        mockMvc.perform(delete("/books/{id}", bookId))
                .andExpect(status().isNoContent());

    }

    @Test
    @DisplayName("Should not delete the book with the provided Id from DB")
    void deleteBookFailure() throws Exception{

        String bookId = "";

        when(bookService.deleteBook(bookId)).thenReturn(ResponseEntity.noContent().build());

        mockMvc.perform(delete("/books/{id}", bookId))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Should return the book with the provided Id from DB")
    void getBookByIdSuccess() throws Exception {

        String id = UUID.randomUUID().toString();
        Book book = new Book(new RequestBookDTO(
                id,
                "The Great Gatsby",
                "F. Scott Fitzgerald",
                "Fiction",
                "9780743273565"
        ));


        when(bookService.getBookById(id)).thenReturn(ResponseEntity.ok(book));


        mockMvc.perform(get("/books/{id}",id))
                .andExpect(status().isOk())  // Verify status is 200 OK
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("The Great Gatsby")))
                .andExpect(jsonPath("$.author", is("F. Scott Fitzgerald")))
                .andExpect(jsonPath("$.isbn", is("9780743273565")));
    }
}
