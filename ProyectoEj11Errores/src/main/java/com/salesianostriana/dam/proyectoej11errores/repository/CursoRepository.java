package com.salesianostriana.dam.proyectoej11errores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.proyectoej11errores.model.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long>{

}
