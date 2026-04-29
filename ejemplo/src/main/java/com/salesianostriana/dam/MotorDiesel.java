package com.salesianostriana.dam;

import org.springframework.stereotype.Component;

@Component
public class MotorDiesel implements Motor {

	@Override
	public void arrancar() {
		System.out.println("Motor diesel arrancando");

	}

	@Override
	public void acelerar() {
		System.out.println("Motor diesel acelerando");

	}

	@Override
	public void frenar() {
		System.out.println("Motor diesel frenando");

	}

	@Override
	public void apagar() {
		System.out.println("Motor diesel apagando");

	}

}
