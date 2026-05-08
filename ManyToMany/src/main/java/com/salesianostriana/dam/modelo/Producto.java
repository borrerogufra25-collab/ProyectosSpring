package com.salesianostriana.dam.modelo;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "producto_tag",
    joinColumns = @JoinColumn(name="producto_id"),
    foreignKey = @ForeignKey(name = "fk_producto_producto_tag"),
        inverseJoinColumns = @JoinColumn(name="tag_id"),
        inverseForeignKey = @ForeignKey(name="fk_tag_producto_tag")
	)
	@Builder.Default
	private Set<Tag> tags = new HashSet<>(); 
	
	
	
	
	
	
	// Métodos helper de la asociación bidireccional
	// Producto - Categoria
	public void addToCategoria(Categoria categoria) {
		this.categoria = categoria;
		categoria.getProductos().add(this);
	}
	
	public void removeFromCategoria(Categoria categoria) {
		categoria.getProductos().remove(this);
		this.categoria = null;
	}
	
	// Métodos helper de la asociación bidireccional
	// Producto - Tag
	public void addTag(Tag tag) {
		this.tags.add(tag);
		tag.getProductos().add(this);
	}

	public void removeTag(Tag tag) {
		tag.getProductos().remove(this);
		this.tags.remove(tag);
	}
	
}
