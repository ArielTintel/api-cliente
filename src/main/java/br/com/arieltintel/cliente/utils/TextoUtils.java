package br.com.arieltintel.cliente.utils;

import org.springframework.util.StringUtils;

public class TextoUtils {

    public static String removeCaracterEspecial(String valueCaracter){
        if(valueCaracter != null){
            return valueCaracter.replaceAll("[^0-9]", "");
        }
        return null;
    }
    
    public static Boolean contemTexto(String texto){
        return StringUtils.hasText(texto);
    }
}
