package com.example.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class Error {
    private int statusCode;
    private String message;
    private LocalDateTime dateTime;
}
