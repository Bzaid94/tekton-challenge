package com.tekton.web.handler;

import com.tekton.bean.ResponseWS;
import com.tekton.exception.ExternalServiceUnavailableException;
import com.tekton.exception.NoCachedPercentageException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.ConnectException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoCachedPercentageException.class)
    public ResponseEntity<ResponseWS> handleNoCache(NoCachedPercentageException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseWS(1, ex.getMessage(), false, null));
    }

    @ExceptionHandler(ExternalServiceUnavailableException.class)
    public ResponseEntity<ResponseWS> handleExternal(ExternalServiceUnavailableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body(new ResponseWS(1, ex.getMessage(), false, null));
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ResponseWS> handleDatabase(DataAccessException ex) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(new ResponseWS(1, "Database unavailable", false, null));
    }

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<ResponseWS> handleGeneric(Exception ex) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(new ResponseWS(1, "Database unavailable", false, null));
    }
}