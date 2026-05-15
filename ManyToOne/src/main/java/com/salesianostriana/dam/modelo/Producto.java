package com.salesianostriana.dam.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Producto {

	@Id @GeneratedValue
	private Long id;
	
	private String nombre;
	private double precio;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_categoria",
		foreignKey = @ForeignKey(name = "fk_producto_categoria"))
	private Categoria categoria;
	
}
