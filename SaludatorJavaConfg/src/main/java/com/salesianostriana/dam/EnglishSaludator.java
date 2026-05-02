package com.salesianostriana.dam;

public class EnglishSaludator implements Saludator {

	@Override
	public String saludar() {
		return "Hello World!";
	}

	@Override
	public String saludar(String msg) {
		return "Hello, " + msg;
	}

}
