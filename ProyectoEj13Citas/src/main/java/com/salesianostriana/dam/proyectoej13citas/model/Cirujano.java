package com.salesianostriana.dam.proyectoej13citas.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cirujano {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String especialidad; // Ej: "Ciber-óptica", "Neuro-conectividad"
    private String rango;        // Ej: "Senior", "Master"

    // Relación inversa: Un cirujano puede atender muchas citas
    @OneToMany(mappedBy = "cirujano")
    private List<Cita> citas = new ArrayList<>();
}