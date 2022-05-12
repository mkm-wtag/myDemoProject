package com.kabir.mydemoproject.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResourceNotFoundException extends RuntimeException {

    private ErrorMessage errorMessage;
}