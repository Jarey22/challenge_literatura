package com.jarey.godliterature.challenge_literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Resultados(@JsonAlias("count")  int contador,
                         @JsonAlias("next")  String siguiente,
                         @JsonAlias("previous")  String anterior,
                         @JsonAlias("results") List<Libro> resultado) {
}
