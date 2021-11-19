package com.kishor.tutrialapp;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TutorialAppApplication {

	@Bean
	public ModelMapper geModelMapper() {
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(TutorialAppApplication.class, args);
	}

}
