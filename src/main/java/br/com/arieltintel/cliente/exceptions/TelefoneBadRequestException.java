package br.com.arieltintel.cliente.exceptions;

public class TelefoneBadRequestException extends RuntimeException{

    public TelefoneBadRequestException(String errorMessage) {
        super(errorMessage);
    }

    public TelefoneBadRequestException() {
        super("Requisição de Telefone Inválida.");
    }
}
