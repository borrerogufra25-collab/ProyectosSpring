package com.salesianostriana.dam.proyectoej13citas.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaHora; // Fecha y hora programada para la cita
    private String salaQuirofano;    // Ej: "Sala Delta-4", "Laboratorio 01"

    // Relación 1: Muchas citas pueden pertenecer a un mismo Paciente
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    // Relación 2: Muchas citas pueden ser asignadas a un mismo Cirujano
    @ManyToOne
    @JoinColumn(name = "cirujano_id")
    private Cirujano cirujano;
}