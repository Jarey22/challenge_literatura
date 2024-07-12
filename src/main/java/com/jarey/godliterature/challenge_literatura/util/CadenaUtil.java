package com.jarey.godliterature.challenge_literatura.util;

public class CadenaUtil {
    public static String limitarLaLongitud(String cadena, int longitudMaxima) {
        if (cadena.length() <= longitudMaxima) {
            return cadena;
        } else {
            return cadena.substring(0, longitudMaxima);
        }
    }
}
