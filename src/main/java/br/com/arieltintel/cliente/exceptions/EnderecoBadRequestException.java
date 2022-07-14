package br.com.arieltintel.cliente.exceptions;

public class EnderecoBadRequestException extends RuntimeException {

    public EnderecoBadRequestException(String errorMessage) {
        super(errorMessage);
    }

    public EnderecoBadRequestException() {
        super("Requisição de Endereço Inválida.");
    }

}
