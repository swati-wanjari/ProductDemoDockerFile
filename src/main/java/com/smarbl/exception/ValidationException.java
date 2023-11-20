package com.smarbl.exception;

import org.springframework.http.HttpStatus;

public class ValidationException extends AppExceptionBase {
    public ValidationException(String message, String reason) {
        super(message, HttpStatus.BAD_REQUEST, ErrorCode.ERR_INVALID_REQUEST, reason);
    }
}