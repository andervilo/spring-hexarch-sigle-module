package org.health.pacientes.application.advice;

import lombok.extern.slf4j.Slf4j;
import org.health.pacientes.infra.exceptions.NotSameIdOnUpdateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class NotSameIdOnUpdateExceptionExceptionHandler {

    @ExceptionHandler(NotSameIdOnUpdateException.class)
    public ResponseEntity<ErrorEntity> processException(NotSameIdOnUpdateException exception){
        ErrorEntity errorEntity = new ErrorEntity(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                Arrays.asList("Id's informados no path e no body são difrentes!"));

        log.error("Erro ao validar requisição {}", errorEntity);

        return new ResponseEntity<>(errorEntity, HttpStatus.BAD_REQUEST);
    }
}
