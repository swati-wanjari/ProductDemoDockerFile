package com.smarbl.exception;

import org.springframework.http.HttpStatus;

public class DataIntegrityViolationException extends AppExceptionBase {
    public DataIntegrityViolationException(String message, String reason) {
        super(HttpStatus.valueOf(message), ErrorCode.ERR_DB, reason);
    }
}
