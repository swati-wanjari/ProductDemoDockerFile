package com.smarbl.exception;

import org.springframework.http.HttpStatus;

public abstract class ApiExceptionBase extends AppExceptionBase {
    public ApiExceptionBase(String message, HttpStatus httpStatus, ErrorCode errorCode, String reason) {
        super(message, httpStatus, errorCode, reason);
    }
}
