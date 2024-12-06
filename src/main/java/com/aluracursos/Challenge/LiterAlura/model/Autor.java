package com.aluracursos.Challenge.LiterAlura.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private Long fechaNacimiento;
    private Long fechaFallecimiento;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros;

    public Autor(){}

    public Autor(DatosAutor datosAutors){
        this.nombre = datosAutors.nombre();
        this.fechaNacimiento = datosAutors.fechaNacimiento();
        this.fechaFallecimiento = datosAutors.fechaFallecimiento();
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Long fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Long getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public void setFechaFallecimiento(Long fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return
                "nombre= " + nombre + '\n' +
                "fechaNacimiento= " + fechaNacimiento + '\n' +
                "fechaFallecimiento= " + fechaFallecimiento + '\n';
    }
}
