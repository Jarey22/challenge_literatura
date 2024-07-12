package com.jarey.godliterature.challenge_literatura.repository;

import com.jarey.godliterature.challenge_literatura.entidad.Entidad_Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IAutor extends JpaRepository<Entidad_Autor, Long> {

    @Query("SELECT a FROM Entidad_Autor a WHERE :anio between a.fechaNacimiento AND a.fechaFallecimiento")
    List<Entidad_Autor> findForYear(int anio);
}
