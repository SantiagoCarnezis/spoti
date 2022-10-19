package com.scarnezis.spoti.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class TerminateSongReproductionException  extends RuntimeException{

  public TerminateSongReproductionException(String message) {
    super(message);
  }
}
