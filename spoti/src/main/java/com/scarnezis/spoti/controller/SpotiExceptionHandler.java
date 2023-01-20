package com.scarnezis.spoti.controller;

import com.scarnezis.spoti.exceptions.ResourceAlreadyExistsException;
import com.scarnezis.spoti.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SpotiExceptionHandler {

  @ExceptionHandler(value= ResourceNotFoundException.class)
  protected ResponseEntity<String> resourceNotFound(RuntimeException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
  }

  @ExceptionHandler(value= ResourceAlreadyExistsException.class)
  protected ResponseEntity<String> resourceAlreadyExists(RuntimeException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
  }
}
