package com.salesianostriana.dam.proyectoej01holamundo;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Lombok hace ahorrarte escribir los contructores, getter and setters, etc.
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persona {

	private String nombre;
	private String apellidos;
	private double notaMedia;
	private LocalDate fecha;

}
