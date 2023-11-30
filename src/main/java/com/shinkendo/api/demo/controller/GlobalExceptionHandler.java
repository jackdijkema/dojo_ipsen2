package com.shinkendo.api.demo.controller;

import com.shinkendo.api.demo.exception.NotFoundException;
import com.shinkendo.api.demo.model.ApiResponse;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({NotFoundException.class, EmptyResultDataAccessException.class})
    protected ApiResponse<Object> handleEntryNotFound(Exception ex) {
        return new ApiResponse<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ApiResponse<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        return new ApiResponse<>(ex.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
    }
}