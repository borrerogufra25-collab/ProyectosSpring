package com.salesianostriana.dam.proyectoej13citas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.proyectoej13citas.model.Cirujano;

@Repository
public interface CirujanoRepository extends JpaRepository<Cirujano, Long> {
    // Operaciones CRUD automáticas para los neuro-cirujanos
}