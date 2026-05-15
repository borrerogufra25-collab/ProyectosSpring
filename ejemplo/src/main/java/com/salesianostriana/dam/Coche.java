package com.salesianostriana.dam;

import org.springframework.stereotype.Component;

@Component
public class Coche {
	
	private Motor motor;
	
	public Coche(Motor motor) {
		this.motor = motor;
	}
	
	public void arrancar() {
		System.out.println("El coche está arrancando");
		motor.arrancar();
	}

}
