package com.jarey.godliterature.challenge_literatura.repository;
import com.jarey.godliterature.challenge_literatura.entidad.Entidad_Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ILibro extends JpaRepository<Entidad_Libro, Long> {
    @Query("SELECT l FROM Entidad_Libro l WHERE l.lenguaje >= :idioma")
    List<Entidad_Libro> findForLanguaje(String idioma);
}
