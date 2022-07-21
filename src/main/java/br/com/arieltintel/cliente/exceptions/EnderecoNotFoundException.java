package br.com.arieltintel.cliente.exceptions;

public class EnderecoNotFoundException extends RuntimeException {

    public EnderecoNotFoundException() {
        super("Endereço não encontrado.");
    }

    public EnderecoNotFoundException(String errorMessage) {
        super(errorMessage);
    }

}
