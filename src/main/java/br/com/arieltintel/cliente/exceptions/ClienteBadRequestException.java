package br.com.arieltintel.cliente.exceptions;

public class ClienteBadRequestException extends RuntimeException {

    public ClienteBadRequestException(String errorMessage) {
        super(errorMessage);
    }

    public ClienteBadRequestException() {
        super("Requisição de Cliente Inválida.");
    }

}
