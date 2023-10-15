package br.com.mizack.sorteador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class SorteadorApplication {

	public static void main(String[] args) {
		Dotenv.configure().load();
		SpringApplication.run(SorteadorApplication.class, args);
	}

}
