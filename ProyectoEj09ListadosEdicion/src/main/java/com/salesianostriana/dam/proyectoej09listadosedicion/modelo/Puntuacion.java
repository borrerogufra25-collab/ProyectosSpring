package com.salesianostriana.dam.proyectoej09listadosedicion.modelo;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity @NoArgsConstructor @AllArgsConstructor @Data
public class Puntuacion {
	
	@Id
	@GeneratedValue
	private Long id;
	
	/*La anotación @CreatedDate en Spring, 
	 * parte del módulo Spring Data, 
	 * se utiliza para marcar automáticamente un campo en 
	 * una entidad JPA con la fecha y hora en que 
	 * fue creada (insertada) en la base de datos.
	 * Es una herramienta de auditoría que evita tener 
	 * que establecer manualmente la fecha de creación en 
	 * cada operación de guardado*/

	@CreatedDate
	private LocalDate fecha;
	
	//No se gestiona en este ejemplo la asociación entera 
	//con puntuaciones, se deja para vosotros
	private int puntuacion;
	
	@ManyToOne
	private Producto producto;
	
	//Helpers de la asociación, aunque no se gestiona en este ejemplo
	public Puntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
	
	public Puntuacion(int puntuacion, Producto producto) {
		this.puntuacion = puntuacion;
		this.producto = producto;
	}
	

}
