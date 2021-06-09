package com.br.zupacademy.proposta.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
public class ApiFase04PropostaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiFase04PropostaApplication.class, args);
		
	}

}
