package net.microservices.Customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorDetails> mapException1(CustomException e)
    {
        ErrorDetails error = new ErrorDetails("Your Input is Invalid", e.getMessage());
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> mapException4(Exception e)
    {
        ErrorDetails error = new ErrorDetails("Your Input is Invalid", "Kindly check the Details and Enter them correctly");
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
