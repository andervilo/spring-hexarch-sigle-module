package org.health.pacientes.domain.pessoa.exceptions;

public class DomainNotFoundException extends RuntimeException {
    public DomainNotFoundException(String message){
        super(message);
    }
}
