package com.scarnezis.spoti.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
public class AlreadyExistsElementException extends RuntimeException{

  public AlreadyExistsElementException(String message) {
    super(message);
  }
}
