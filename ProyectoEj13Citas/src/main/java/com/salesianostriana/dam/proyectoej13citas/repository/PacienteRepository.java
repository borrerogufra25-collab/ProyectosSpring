package com.salesianostriana.dam.proyectoej13citas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.proyectoej13citas.model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    // Ya incluye findAll(), findById(), save(), deleteById(), etc.
}