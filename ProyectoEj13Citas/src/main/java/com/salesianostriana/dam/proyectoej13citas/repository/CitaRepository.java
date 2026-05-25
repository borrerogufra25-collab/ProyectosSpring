package com.salesianostriana.dam.proyectoej13citas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.proyectoej13citas.model.Cita;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    // Al extender de JpaRepository, Spring gestionará las consultas 
    // resolviendo automáticamente las relaciones con Paciente y Cirujano
}