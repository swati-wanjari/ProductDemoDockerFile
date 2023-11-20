package com.smarbl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends AppExceptionBase {
    public ResourceNotFoundException() {
        super("Resource not found", HttpStatus.NOT_FOUND, ErrorCode.ERR_INVALID_REQUEST, "Bad request url");
    }
}
