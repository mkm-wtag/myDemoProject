package com.kabir.mydemoproject.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerLogoutException extends RuntimeException {

    private ErrorMessage errorMessage;
}