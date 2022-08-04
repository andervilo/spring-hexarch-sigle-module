package org.health.pacientes.application.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class FieldRequiredExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorEntity> processException(MethodArgumentNotValidException exception){

        List list = exception
                    .getBindingResult()
                    .getAllErrors().stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .collect(Collectors.toList());

        return new ResponseEntity<>(new ErrorEntity(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), list), HttpStatus.BAD_REQUEST);
    }
}
