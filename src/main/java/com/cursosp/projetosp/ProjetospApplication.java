package com.cursosp.projetosp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;

@SpringBootApplication
public class ProjetospApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProjetospApplication.class, args);
	}

	@Override
	public void run(String... args) throws ParseException {

	}
}