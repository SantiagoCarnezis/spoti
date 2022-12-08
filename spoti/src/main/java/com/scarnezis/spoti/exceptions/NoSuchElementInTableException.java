package com.scarnezis.spoti.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NoSuchElementInTableException extends RuntimeException{

  public NoSuchElementInTableException(String message) {
    super(message);
  }
}
