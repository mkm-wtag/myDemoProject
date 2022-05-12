package com.kabir.mydemoproject.exception;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerLoginException extends RuntimeException {

    private ErrorMessage errorMessage;

}