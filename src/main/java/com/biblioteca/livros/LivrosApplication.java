package com.biblioteca.livros;

import com.biblioteca.livros.principal.Principal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LivrosApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(LivrosApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.showMenu();
	}

}
