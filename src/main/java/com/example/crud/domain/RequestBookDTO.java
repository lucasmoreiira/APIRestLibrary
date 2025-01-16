package com.example.crud.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

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

