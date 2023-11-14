package com.example.onlinestore_number1.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String exceptionMessage){
        super(exceptionMessage);
    }
}
