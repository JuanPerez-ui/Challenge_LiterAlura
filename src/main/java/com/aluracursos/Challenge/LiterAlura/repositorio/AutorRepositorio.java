package com.aluracursos.Challenge.LiterAlura.repositorio;

import com.aluracursos.Challenge.LiterAlura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepositorio extends JpaRepository<Autor,Long> {
    Autor findByNombreContainsIgnoreCase(String nombre);

    @Query( "SELECT s FROM Autor s WHERE s.fechaFallecimiento >= :anioAutor AND " +
            "s.fechaNacimiento <= :anioAutor" )
    List<Autor> autoresVivoAnio(int anioAutor);
}
