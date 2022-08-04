package org.health.pacientes.application.advice;

import lombok.extern.slf4j.Slf4j;
import org.health.pacientes.domain.pessoa.exceptions.DomainNotFoundException;
import org.health.pacientes.infra.exceptions.NotSameIdOnUpdateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class DomainNotFoundExceptionExceptionHandler {

    @ExceptionHandler(DomainNotFoundException.class)
    public ResponseEntity<ErrorEntity> processException(DomainNotFoundException exception){

        ErrorEntity errorEntity = new ErrorEntity(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                Arrays.asList(exception.getMessage()));

        log.error("Erro ao validar requisição {}", errorEntity);

        return new ResponseEntity<>(errorEntity, HttpStatus.NOT_FOUND);
    }
}
