package com.kabir.mydemoproject.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AllExceptionHandlers {


    @ExceptionHandler(InvalidRequestBodyException.class)
    public ResponseEntity<ErrorMessage> handleInvalidRequestBodyException(InvalidRequestBodyException exception) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(exception.getBindingResult().getFieldError().getField() + " "
                + exception.getBindingResult().getFieldError().getDefaultMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorMessage);
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorMessage> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage("User with given email already exists");

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
    }


    @ExceptionHandler(CustomerLoginException.class)
    public ResponseEntity<ErrorMessage> handleCustomerLoginException(CustomerLoginException exception) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(exception.getErrorMessage());
    }

    @ExceptionHandler(CustomerLogoutException.class)
    public ResponseEntity<ErrorMessage> handleCustomerLogoutException(CustomerLogoutException exception) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(exception.getErrorMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleResourceNotFoundException(ResourceNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getErrorMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorMessage> handleAccessDeniedException(AccessDeniedException exception) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(exception.getErrorMessage());
    }

    @ExceptionHandler(SeatAlreadyBookedException.class)
    public ResponseEntity<ErrorMessage> handleSealAlreadyBookedException(SeatAlreadyBookedException exception){
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(exception.getErrorMessage());
    }

}