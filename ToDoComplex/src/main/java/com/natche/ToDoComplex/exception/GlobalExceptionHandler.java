package com.natche.ToDoComplex.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.natche.ToDoComplex.dto.ExceptionResponseDTO;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourseAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponseDTO> handelResourseAlreadyExistsException(ResourseAlreadyExistsException e,
            WebRequest webRequest) {
        ExceptionResponseDTO eDto = new ExceptionResponseDTO(webRequest.getDescription(false), HttpStatus.CONFLICT,
                e.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(eDto);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> handelResourceNotFoundException(ResourceNotFoundException e,
            WebRequest webRequest) {
        ExceptionResponseDTO eDto = new ExceptionResponseDTO(webRequest.getDescription(false), HttpStatus.NOT_FOUND,
                e.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(eDto);
    }

    //we write this wildcard exception below
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDTO> handelGlobalException(Exception e,
            WebRequest webRequest) {
        ExceptionResponseDTO eDto = new ExceptionResponseDTO(webRequest.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR,
                e.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(eDto);
    }

}