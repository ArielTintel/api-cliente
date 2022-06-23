package br.com.arieltintel.cliente.exceptions;

public class ClienteNotFoundException extends RuntimeException {

    public ClienteNotFoundException() {
        super("Cliente não encontrado.");
    }

    public ClienteNotFoundException(String errorMessage) {
        super(errorMessage);
    }

}
