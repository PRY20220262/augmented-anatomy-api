package com.pry20220262.augmentedanatomy.exception;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ServiceException extends RuntimeException {
  
  private static final long serialVersionUID = 1L;
  
  private final Error error;
  
  public ServiceException(Error error) {
    this.error = error;
  }
  
  
  
  
}