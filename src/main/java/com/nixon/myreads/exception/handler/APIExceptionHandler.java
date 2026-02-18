package com.nixon.myreads.exception.handler;

import com.nixon.myreads.exception.BadRequestException;
import com.nixon.myreads.exception.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
public class APIExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<StandardErrorResponse> handleBadRequest(BadRequestException badRequestException, HttpServletRequest request) {
        StandardErrorResponse errorResponse = new StandardErrorResponse
                (
                        HttpStatus.BAD_REQUEST.value(),
                        OffsetDateTime.now(),
                        request.getServletPath(),
                        badRequestException.getMessage(),
                        null
                );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<StandardErrorResponse> handleEntityNotFound(EntityNotFoundException e, HttpServletRequest request) {
        StandardErrorResponse errorResponse = new StandardErrorResponse
                (
                        HttpStatus.NOT_FOUND.value(),
                        OffsetDateTime.now(),
                        request.getServletPath(),
                        e.getMessage(), null
                );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<StandardErrorResponse> handleException(Exception e, HttpServletRequest request) {
        StandardErrorResponse response = new StandardErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                OffsetDateTime.now(),
                request.getServletPath(),
                e.getMessage(), null
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardErrorResponse> handleException(MethodArgumentNotValidException exception, HttpServletRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            errors.add(new ValidationError(error.getField(), error.getDefaultMessage()));
        }
        StandardErrorResponse response = new StandardErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                OffsetDateTime.now(),
                request.getServletPath(),
                "Argument validation failed",errors
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


}
