package com.aluracursos.Challenge.LiterAlura;

import com.aluracursos.Challenge.LiterAlura.principal.Principal;
import com.aluracursos.Challenge.LiterAlura.repositorio.AutorRepositorio;
import com.aluracursos.Challenge.LiterAlura.repositorio.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeLiterAluraApplication implements CommandLineRunner {

	@Autowired
	private LibroRepositorio libroRepositorio;
	@Autowired
	private AutorRepositorio autorRepositorio;

	public static void main(String[] args) {
		SpringApplication.run(ChallengeLiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(libroRepositorio,autorRepositorio);
		principal.muestraElMenu();

	}
}
