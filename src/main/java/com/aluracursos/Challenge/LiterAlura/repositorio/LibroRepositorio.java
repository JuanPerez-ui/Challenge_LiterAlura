package com.aluracursos.Challenge.LiterAlura.repositorio;

import com.aluracursos.Challenge.LiterAlura.model.Autor;
import com.aluracursos.Challenge.LiterAlura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibroRepositorio extends JpaRepository<Libro, Long> {
    Libro findByTituloContainsIgnoreCase(String titulo);

    @Query( "SELECT s FROM Libro s WHERE s.idioma = :idiomaLibros" )
    List<Libro> encontrarLibroPorIdioma(String idiomaLibros);
}
