package com.jarey.godliterature.challenge_literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Libro(
        @JsonAlias("id") String id,
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<Autor> autores,
        @JsonAlias("languages") List<String> lenguaje,
        @JsonAlias("download_count") Integer descarga
) {
}
