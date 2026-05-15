package com.salesianostriana.dam.proyectoej03operacionesycondicionales;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Producto {
	
	private String nombre;
	private String descripcion;
	private float precio;
	private int numeroVotos;
	private float porcentajeSatisfaccion;
	
	/*
	 * Si vamos a usar un constructor diferente al que tiene todos o ningún argumento
	 * hay que escribirlo en la clase, por ejemplo, este de debajo con solo 3 argumentos
	 * */
	public Producto (String nombre, String descripcion, float precio) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
	}
	
}