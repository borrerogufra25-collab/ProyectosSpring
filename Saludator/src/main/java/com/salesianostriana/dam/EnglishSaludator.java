package com.salesianostriana.dam;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
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
