package com.salesianostriana.dam.proyectoej01holamundo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/* Anotaciones de Lombok
 * 
 * @Data proporciona: toString, equals, hashCode, getters and setters y 
 * constructor que inicializa todos los campos finales
 * @NoArgsConstructor crea constructor vacío
 * @AllArgsConstructor crea el constructor con todos los atributos
 * 
 * Se pueden ver todos los métodos y constructores en la pestaña del menú Outline
 * */
@Data @NoArgsConstructor @AllArgsConstructor
public class Persona {

	private String nombre;
	private String apellidos;
	
}