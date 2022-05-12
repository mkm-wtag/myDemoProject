package com.kabir.mydemoproject.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.BindingResult;

@Data
@AllArgsConstructor
public class InvalidRequestBodyException extends RuntimeException {

    private BindingResult bindingResult;

}