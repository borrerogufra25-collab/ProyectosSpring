package com.salesianostriana.dam;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class SaludatorConfig {


	@Bean
	Saludator spanishSaludator() {
		return new SpanishSaludator();
	}
	
	@Primary
	@Bean
	Saludator englishSaludator() {
		return new EnglishSaludator();
	}

}
