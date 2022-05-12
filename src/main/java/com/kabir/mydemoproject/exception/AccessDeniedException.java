package com.kabir.mydemoproject.exception;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccessDeniedException extends RuntimeException {

    private ErrorMessage errorMessage;
}
