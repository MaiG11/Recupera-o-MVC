package com.maira.mediaescolar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlunoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlunoApplication.class, args);
		System.out.println("Aplicação Media Escolar iniciada com sucesso!");
		System.out.println("Acesse: http://localhost:8080");
	}
	}


