package com.salesianostriana.dam.Saludator;

public class SpanishSaludator implements Saludator {

	@Override
	public String saludar() {

		return "Cosas";
	}

	@Override
	public String saludarConMensaje(String smg) {

		return "Cosas" + smg;
	}

}
