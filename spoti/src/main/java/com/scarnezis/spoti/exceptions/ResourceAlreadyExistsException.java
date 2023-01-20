package com.scarnezis.spoti.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourceAlreadyExistsException extends RuntimeException{

  public ResourceAlreadyExistsException(String resource) {
    super(resource + " already exists");
  }
}
