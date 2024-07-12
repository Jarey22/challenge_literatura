package com.jarey.godliterature.challenge_literatura.map;

public interface IConvertirDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
