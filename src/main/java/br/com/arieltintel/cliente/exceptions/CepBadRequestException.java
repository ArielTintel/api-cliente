package br.com.arieltintel.cliente.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CepBadRequestException extends ResponseStatusException {

    public CepBadRequestException() {
        super(HttpStatus.BAD_REQUEST, "Cep com formato inválido.");
    }

    public CepBadRequestException(String cep) {
        super(HttpStatus.BAD_REQUEST, "Cep: " + cep + " com formato inválido.");
    }
}
