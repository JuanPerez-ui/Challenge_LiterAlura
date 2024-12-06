package com.aluracursos.Challenge.LiterAlura.principal;

import com.aluracursos.Challenge.LiterAlura.model.*;
import com.aluracursos.Challenge.LiterAlura.repositorio.AutorRepositorio;
import com.aluracursos.Challenge.LiterAlura.repositorio.LibroRepositorio;
import com.aluracursos.Challenge.LiterAlura.service.ConsumoApi;
import com.aluracursos.Challenge.LiterAlura.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private ConsumoApi consumoAPI = new ConsumoApi();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);
    private LibroRepositorio libroRepositorio;
    private AutorRepositorio autorRepositorio;
    private Optional<Libro> libroBuscado;
    private List<Libro> libros;
    private List<Autor> autores;
    private Autor autor= null;



    public Principal(LibroRepositorio libroRepository, AutorRepositorio autorRepository) {

        this.libroRepositorio= libroRepository;
        this.autorRepositorio = autorRepository;
    }

    public void muestraElMenu(){
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libro por título
                    2 - Listar libros registrados
                    3 - Listar autores registrado
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                                     
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresResgistrados();
                    break;
                case 4:
                    listarAutoresVivoAnio();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;

                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void listarLibrosPorIdioma() {
        libros=libroRepositorio.findAll();

    List<String> idiomaLibro = libros.stream()
            .map(i->i.getIdioma())
            .collect(Collectors.toSet())
            .stream()
            .collect(Collectors.toList());
        System.out.println("Idiomas Disponible : " + idiomaLibro);
        System.out.println("Ingrese el idioma de los libros a buscar");
        var idiomaLibros = teclado.next();
        libros=libroRepositorio.encontrarLibroPorIdioma(idiomaLibros);
        if (libros.isEmpty())
        {
            System.out.println("No se encontraron libros o fue mal escrito el " +
                    "idioma");
        }else {
            System.out.println("Libros encontrados segun idioma: " + idiomaLibros + '\n');
            libros.stream()
                    .forEach(System.out::println);
        }

    }

    private void listarAutoresVivoAnio() {
        System.out.println("Ingrese el año para la busqueda de Autores");
        var anioAutor = teclado.nextInt();
        autores= autorRepositorio.autoresVivoAnio(anioAutor);
        if (autores.isEmpty())
        {
            System.out.println("No se encontraron Autores en el año indicado (" +
                    anioAutor + ")");
        }
        else{
            autores.stream()
                    .forEach(System.out::println);
        }

    }

    private void listarAutoresResgistrados() {
        System.out.println("Autores Registrados");
        autores = autorRepositorio.findAll();
        if (autores.isEmpty())
        {
            System.out.println("No hay Autores registrados");
        }else
        {
            autores.stream()
                    .forEach(System.out::println);
        }
    }

    private void listarLibrosRegistrados() {
        System.out.println("Libros registrados");
        libros = libroRepositorio.findAll();
        if(libros.isEmpty())
        {
            System.out.println("No hay ningún Libro registrado");
        }else {
            libros.stream()
                    .forEach(System.out::println);
        }
    }


    private DatosResultados getDatosLibro() {
        System.out.println("Escribe el titulo del libro que deseas buscar");
        var nombreLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + nombreLibro.replace(" ", "%20"));
      //  System.out.println(json);
        DatosResultados datos = conversor.ObtenerDatos(json, DatosResultados.class);
        return datos;
    }



    void buscarLibroPorTitulo(){
        DatosResultados datos = getDatosLibro();
        if (datos.resultado().isEmpty())
        {
            System.out.println("Lo siento, el libro buscado no se encuentra o fue " +
                    "mal escrito");
        }else {

            DatosLibro libroEncontrado = datos.resultado().get(0);
            DatosAutor autorEncontrado = libroEncontrado.autor().get(0);


            Libro reporteLibro = libroRepositorio.findByTituloContainsIgnoreCase(
                    libroEncontrado.titulo());

            Autor reporteAutor = autorRepositorio.findByNombreContainsIgnoreCase(
                    autorEncontrado.nombre());

            if (reporteLibro == null) {
                if (reporteAutor == null) {
                    autor = new Autor(autorEncontrado);
                    if (autor != null) {
                        System.out.println("Se ha guardado un nuevo Autor");
                        autor = autorRepositorio.save(autor);
                        System.out.println("Se ha guardado un nuevo Libro");
                        Libro libro = new Libro(libroEncontrado, autor);
                        libroRepositorio.save(libro);
                    }else {
                        System.out.println("El valor del autor es NULL");
                        System.out.println("No se pudo guardar el Autor");
                        System.out.println("No se pudo guardar el Libro");
                    }
                } else {
                        System.out.println("El autor ya se encuentra registrado");
                        System.out.println("Se ha guardado un nuevo Libro");
                        Libro libro = new Libro(libroEncontrado,reporteAutor);
                }
            }else
            {
                System.out.println("El Autor ya se encuentra en la base de datos");
                System.out.println("El Libro ya se encuentra en la base de datos");
            }
        }

   }
}
