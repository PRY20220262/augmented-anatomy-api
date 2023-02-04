package com.pry20220262.augmentedanatomy.exception;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
  
  private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

  @ExceptionHandler
  protected ResponseEntity<ErrorResponse> handleException(ServiceException exc) {
    logger.error(exc.toString(), exc);
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    return buildResponseEntity(exc, httpStatus, null);
  }

  @ExceptionHandler
  protected ResponseEntity<ErrorResponse> handleException(InvalidDataException exc) {
    logger.error(exc.toString(), exc);
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    ServiceException sexc = new ServiceException(exc.getError());

    List<String> errores = exc.getResult()
        .getFieldErrors()
        .stream()
        .map(FieldError::getDefaultMessage)
        .collect(Collectors.toList());
    return buildResponseEntity(sexc, httpStatus, errores);
  }
  
  @ExceptionHandler
  protected ResponseEntity<ErrorResponse> handleException(MethodArgumentTypeMismatchException exc) {
    logger.error(exc.toString(), exc);
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    ServiceException sexc = new ServiceException(Error.INVALID_DATA);
    return buildResponseEntity(sexc, httpStatus, null);
  }

  @ExceptionHandler
  protected ResponseEntity<ErrorResponse> handleException(Exception exc) {
    logger.error(exc.toString(), exc);
    HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    ServiceException sexc = new ServiceException(Error.INVALID_DATA);
    return buildResponseEntity(sexc, httpStatus, null);
  } 
  
  private ResponseEntity<ErrorResponse> buildResponseEntity(ServiceException exc, 
      HttpStatus httpStatus, List<String> errorDetail) {
    ErrorResponse errorResponse = ErrorResponse.builder()
        .codError(exc.getError().getCodError())
        .message(exc.getError().getMessage())
        .timestamp(new Date())
        .details(errorDetail)
        .build();
    return new ResponseEntity<>(errorResponse, httpStatus);
  }

}
