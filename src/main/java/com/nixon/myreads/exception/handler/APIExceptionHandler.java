package com.nixon.myreads.exception.handler;

import com.nixon.myreads.exception.BadRequestException;
import com.nixon.myreads.exception.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;
import java.util.Arrays;

@RestControllerAdvice
@RequiredArgsConstructor
public class APIExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<StandardErrorResponse> handleBadRequest(BadRequestException badRequestException, HttpServletRequest request) {
        StandardErrorResponse errorResponse = new StandardErrorResponse
                (
                        HttpStatus.BAD_REQUEST.value(),
                        OffsetDateTime.now(),
                        request.getServletPath(),
                        badRequestException.getMessage()
                );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardErrorResponse> handleEntityNotFound(EntityNotFoundException e, HttpServletRequest request) {
        StandardErrorResponse errorResponse = new StandardErrorResponse
                (
                        HttpStatus.NOT_FOUND.value(),
                        OffsetDateTime.now(),
                        request.getServletPath(),
                        e.getMessage()
                );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    
}
