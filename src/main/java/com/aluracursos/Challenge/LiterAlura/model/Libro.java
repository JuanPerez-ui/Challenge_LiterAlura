package com.aluracursos.Challenge.LiterAlura.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private String idioma;
    private Double numeroDeDescargas;
    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Autor autor;


    public Libro() {
    }

    public Libro(DatosLibro datosLibro, Autor autor) {
        this.titulo = datosLibro.titulo();
        this.autor = autor;
        this.idioma = datosLibro.idioma().getFirst();
        this.numeroDeDescargas = datosLibro.numeroDeDescargas().doubleValue();
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor(Autor autor) {
        return autor;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    @Override
    public String toString() {
        return
                "titulo= " + titulo + '\n' +
                "idioma= " + idioma + '\n' +
                "numeroDeDescargas= " + numeroDeDescargas +'\n';
    }
}
