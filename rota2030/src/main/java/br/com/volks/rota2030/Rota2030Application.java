package br.com.volks.rota2030;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Rota2030Application {

	public static void main(String[] args) {
		SpringApplication.run(Rota2030Application.class, args);
	}

}
