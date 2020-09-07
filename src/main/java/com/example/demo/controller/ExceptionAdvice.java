package com.example.demo.controller;


import com.example.demo.bean.Error;
import com.example.demo.exception.EmployeeIdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(EmployeeIdNotFoundException.class)
    public ResponseEntity<Error> mapEmployeeIdNotFoundException(EmployeeIdNotFoundException e) {
        Error error = new Error(HttpStatus.NOT_FOUND.value(), e.getMessage(),LocalDateTime.now());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> mapException(Exception e) {
        Error error = new Error(HttpStatus.NOT_FOUND.value(), e.getMessage(),LocalDateTime.now());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

}
