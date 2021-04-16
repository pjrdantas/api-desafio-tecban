package br.com.tecban;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class ApiDesafioTecbanApplication {
	
	private static final Logger log = LoggerFactory.getLogger(ApiDesafioTecbanApplication.class);

	
	public static void main(String[] args) {
		SpringApplication.run(ApiDesafioTecbanApplication.class, args);
		log.info("********** API-DESAFIO-TECBAN ESTA PRONTO PARA SER CONSUMIDO! ********** "+ new Date());
	}

}
