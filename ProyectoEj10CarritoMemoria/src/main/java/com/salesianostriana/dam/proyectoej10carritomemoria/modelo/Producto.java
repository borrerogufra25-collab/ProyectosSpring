package com.salesianostriana.dam.proyectoej10carritomemoria.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Producto {
		
	@Id
	@GeneratedValue
	private Long id;
	
	private String nombre;
	
	@Lob 
	private String descripcion;
	
	//Precio base o de venta al público, sin aplicar descuento
	private float pvp;
	
	private float descuento;
	
	private String imagen;

	public Producto(String nombre, String descripcion, float pvp, float descuento, String imagen) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.pvp = pvp;
		this.descuento = descuento;
		this.imagen = imagen;

	}	
	//Precio final, con descuento
	public double getPrecioFinal() {
		return pvp * (1 - descuento);
	}
	
	public boolean tieneDescuento() {
		return (descuento > 0);
	}
}

