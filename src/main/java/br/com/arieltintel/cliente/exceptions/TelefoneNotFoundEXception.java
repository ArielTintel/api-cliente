package br.com.arieltintel.cliente.exceptions;

public class TelefoneNotFoundEXception extends RuntimeException{


    public TelefoneNotFoundEXception() {
        super("Telefone não encontrado.");
    }

    public TelefoneNotFoundEXception(String errorMessage) {
        super(errorMessage);
    }
}
