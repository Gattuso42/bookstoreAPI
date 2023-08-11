package com.gattuso42.BookStoreAPI.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

//    Exception Handlers

    @ExceptionHandler(EntityNotFoundException.class)
    ResponseEntity<Object>EntityNotFoundExceptionHandler(EntityNotFoundException ex){
        CustomExceptionResponse error = new CustomExceptionResponse(List.of(ex.getLocalizedMessage()));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    ResponseEntity<Object>ValidationExceptionHandler(ValidationException ex){
        CustomExceptionResponse error = new CustomExceptionResponse(List.of(ex.getLocalizedMessage()));
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    ResponseEntity<Object>DataIntegrityViolationExceptionHandler(DataIntegrityViolationException ex){
        CustomExceptionResponse error = new CustomExceptionResponse(List.of(ex.getLocalizedMessage()));
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DateTimeParseException.class)
    ResponseEntity<Object>DateTimeParseExceptionHandler(DateTimeParseException ex){
        CustomExceptionResponse error = new CustomExceptionResponse(List.of("The date format is incorrect, it must be 'yyyy-MM-dd'"));
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }


//  Override Exception Methods
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->{
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName,errorMessage);
        }  );
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        CustomExceptionResponse error = new CustomExceptionResponse(List.of("Use the correct type in the path variable"));
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        CustomExceptionResponse error = new CustomExceptionResponse(List.of(ex.getLocalizedMessage()));
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
