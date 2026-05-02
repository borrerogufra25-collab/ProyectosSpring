package com.salesianostriana.dam;

import org.springframework.beans.factory.annotation.Value;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class SpanishSaludator implements Saludator {
	
	@Value("${saludator.dialect:default}")
	private String dialect;
	
	@PostConstruct
	public void postInit() {
		System.out.println(dialect);
	}
	
	@PreDestroy
	public void despedida() {
		System.out.println("Adios campeón");
	}

	@Override
	public String saludar() {
		
		String saludo = switch(dialect) {
		case "andaluz" -> "Hola illo";
		case "murciano" -> "Acho pijo";
		default -> "Hola Mundo";
		};
		
		return saludo;
	}

	@Override
	public String saludar(String msg) {
		return "Hola, " + msg;
	}

}
