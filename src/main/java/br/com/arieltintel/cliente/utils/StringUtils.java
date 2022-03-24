package br.com.arieltintel.cliente.utils;

public class StringUtils {

    public static String removeCaracterEspecial(String valueCaracter){
        if(valueCaracter != null){
            return valueCaracter.replaceAll("[^0-9]", "");
        }
        return null;
    }
}
