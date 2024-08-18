package com.galileo.cu.servicio_modelosbalizas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EntityScan({ "com.galileo.cu.commons.models" })
public class ServicioModelosbalizasApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ServicioModelosbalizasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("**************************************");
		System.out.println("Importador V-2408181856");
	}

}
