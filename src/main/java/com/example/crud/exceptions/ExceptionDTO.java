package com.example.crud.exceptions;


public class ExceptionDTO {

    String message;
    Integer status;

    public ExceptionDTO(String message, Integer status){
        this.message = message;
        this.status = status;
    }

}
