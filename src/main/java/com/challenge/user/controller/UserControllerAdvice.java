package com.challenge.user.controller;

import com.challenge.user.exceptions.UserDoesNotExist;
import com.challenge.user.exceptions.UserExistsException;
import com.challenge.user.model.ApiErrorSto;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("NullableProblems")
@Slf4j
@ControllerAdvice
public class UserControllerAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        String errorMsg = errors.toString();
        ApiErrorSto error = ApiErrorSto.builder()
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(LocalDateTime.now())
                .message(errorMsg).build();

        log.info("Exception occurred: {}", errorMsg);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiErrorSto> handleConstraintViolationException(ConstraintViolationException ex) {

        String errorMsg = ex.getCause().getMessage();
        ApiErrorSto error = ApiErrorSto.builder()
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(LocalDateTime.now())
                .message(errorMsg).build();

        log.info("Exception occurred: {}", errorMsg);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserDoesNotExist.class)
    public ResponseEntity<ApiErrorSto> handleUserNotFound(UserDoesNotExist ex) {

        String errorMsg = ex.getMessage();
        ApiErrorSto error = ApiErrorSto.builder()
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(LocalDateTime.now())
                .message(errorMsg).build();

        log.info("Exception occurred: {}", errorMsg);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<ApiErrorSto> handleUserExistWhenCreating(UserExistsException ex) {

        String errorMsg = ex.getMessage() + " You cannot have users with same emails.";
        ApiErrorSto error = ApiErrorSto.builder()
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(LocalDateTime.now())
                .message(errorMsg).build();

        log.info("Exception occurred: {}", errorMsg);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
