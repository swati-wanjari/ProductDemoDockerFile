package com.smarbl.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
abstract class AppExceptionBase extends Exception {
    private HttpStatus status;
    private ErrorCode errorCode;
    private String errorReason;

    public AppExceptionBase(String message) {
        super(message);
    }

    public AppExceptionBase(String message, HttpStatus status, ErrorCode errorCode, String errorReason) {
        super(message);
        this.status = status;
        this.errorCode = errorCode;
        this.errorReason = errorReason;
    }
}
