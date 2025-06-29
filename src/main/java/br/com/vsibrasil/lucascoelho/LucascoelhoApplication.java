package br.com.vsibrasil.lucascoelho;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LucascoelhoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LucascoelhoApplication.class, args);
		System.out.println("Aplicação Gerador de Anagramas iniciada com sucesso!");
	}

}
