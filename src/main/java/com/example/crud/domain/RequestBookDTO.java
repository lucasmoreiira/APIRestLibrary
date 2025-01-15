package com.example.crud.domain;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RequestBookDTO(String id, String name, String author, String genre, String ISBN) {

}

