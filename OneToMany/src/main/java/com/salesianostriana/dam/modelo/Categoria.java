package com.salesianostriana.dam.modelo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Categoria {

	@Id
	@GeneratedValue
	private Long id;
	private String nombre;

	@OneToMany(mappedBy = "categoria", fetch = FetchType.EAGER)
	@ToString.Exclude
	@Builder.Default
	private List<Producto> productos = new ArrayList<>();

}
