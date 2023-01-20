package com.scarnezis.spoti.exceptions;

public class ResourceNotFoundException extends RuntimeException{

  public ResourceNotFoundException(String message) {
    super(message + " was not found");
  }
}
