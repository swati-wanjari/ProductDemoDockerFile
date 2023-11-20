package com.smarbl.exception;

import com.smarbl.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class AppGlobalExceptionHandler {
    //    private static Logger logger = LoggerFactory.getLogger(AppGlobalExceptionHandler.class);
    @ExceptionHandler({ResourceNotFoundException.class, ValidationException.class, AppExceptionBase.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)

    public final ResponseEntity<ErrorResponse> handleAppException(Exception ex, WebRequest webRequest) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse = new ErrorResponse();

        if (ex instanceof AppExceptionBase) {
            AppExceptionBase appException = (AppExceptionBase) ex;
            if (appException.getStatus() != null) {
                status = appException.getStatus();
            }
            errorResponse.setMessage(ex.getMessage());
            errorResponse.setErrorCode(appException.getErrorCode().toString());
            errorResponse.setErrorReason(appException.getErrorReason());
        }
        errorResponse.setStatus(status.toString());
        return new ResponseEntity<>(errorResponse, status);
    }
}
