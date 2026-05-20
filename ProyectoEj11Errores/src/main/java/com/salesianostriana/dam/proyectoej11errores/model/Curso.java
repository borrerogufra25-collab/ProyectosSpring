package com.salesianostriana.dam.proyectoej11errores.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Curso {

	/*Aquí podemos ver la utilización de algunas validcaciones
	 * automáticas de jakarta.validation como @NoEmpty o @Min*/
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El nombre del curso no puede estar vacío.")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres.")
    private String nombre;

    @NotEmpty(message = "El tutor del curso es obligatorio.")
    private String tutor;

    @NotNull(message = "El número de plazas es obligatorio.")
    @Min(value = 1, message = "El curso debe tener al menos 1 plaza.")
    private Integer plazasMaximas;

    private Integer plazasOcupadas = 0;

    public Curso(String nombre, String tutor, Integer plazasMaximas) {
        this.nombre = nombre;
        this.tutor = tutor;
        this.plazasMaximas = plazasMaximas;
        this.plazasOcupadas = 0;
    }  
}