package br.com.arieltintel.cliente.utils;

import org.springframework.util.StringUtils;

public class TextoUtils {

    public static String removeEspecialCaracter(String valueCaracter){
        if (valueCaracter != null) {
            return valueCaracter.replaceAll("[^0-9]", "");
        }
        return null;
    }
    public static Boolean contemTexto(String texto) {
        return StringUtils.hasText(texto);
    }

    public static String adicionarMascaraCPF(String cpf){
        if (StringUtils.hasText(cpf)){
            return cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
        } return cpf;
    }
}