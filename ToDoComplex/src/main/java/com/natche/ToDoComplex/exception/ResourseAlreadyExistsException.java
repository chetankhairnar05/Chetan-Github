package com.natche.ToDoComplex.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ResourseAlreadyExistsException extends RuntimeException{
    public ResourseAlreadyExistsException(String message){
        super(message);
    }
}
