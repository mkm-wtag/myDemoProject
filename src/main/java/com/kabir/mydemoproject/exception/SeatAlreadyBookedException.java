package com.kabir.mydemoproject.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SeatAlreadyBookedException extends RuntimeException {

    private ErrorMessage errorMessage;
}
