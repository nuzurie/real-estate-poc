package com.nuzurie.realestate.controller;

import com.nuzurie.realestate.dto.ErrorDetails;
import com.nuzurie.realestate.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ResourceNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<ErrorDetails> resourceNotFoundHandler(ResourceNotFoundException ex, WebRequest webRequest) {
        return new ResponseEntity<>(
                new ErrorDetails(new Date(), ex.getMessage(), webRequest.getDescription(false)),
                HttpStatus.NOT_FOUND
                );
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ResponseEntity<ErrorDetails> globalException(Exception ex, WebRequest webRequest) {
        return new ResponseEntity<>(
                new ErrorDetails(new Date(), ex.getMessage(), webRequest.getDescription(false)),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
