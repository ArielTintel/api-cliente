package br.com.arieltintel.cliente.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CepNotFoundException extends ResponseStatusException {

    public CepNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Cep não encontrado.");
    }

    public CepNotFoundException(String cep) {
        super(HttpStatus.NOT_FOUND, "Cep: " + cep + " não encontrado.");
    }
}
