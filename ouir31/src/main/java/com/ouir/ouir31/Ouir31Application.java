package com.ouir.ouir31;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Ouir31Application {

	public static void main(String[] args) {
		SpringApplication.run(Ouir31Application.class, args);
	}

}
