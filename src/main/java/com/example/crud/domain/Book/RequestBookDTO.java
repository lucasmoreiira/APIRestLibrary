package com.example.crud.domain.Book;

import jakarta.validation.constraints.NotNull;

public record RequestBookDTO(

        String id,

        @NotNull(message = "Name cannot be null")
        String name,

        @NotNull(message = "Name cannot be null")
        String author,

        @NotNull(message = "Name cannot be null")
        String genre,

        String ISBN
) {
}

