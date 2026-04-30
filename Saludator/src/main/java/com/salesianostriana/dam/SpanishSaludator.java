package com.salesianostriana.dam;

import org.springframework.stereotype.Service;

@Service
public class SpanishSaludator implements Saludator {

	@Override
	public String saludar() {
		return "Hola Mundo!";
	}

	@Override
	public String saludar(String msg) {
		return "Hola, " + msg;
	}

}
