package com.jarey.godliterature.challenge_literatura.model;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Autor(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") Integer cumpleanios,
        @JsonAlias("death_year") Integer aniomuerte
) {
}
