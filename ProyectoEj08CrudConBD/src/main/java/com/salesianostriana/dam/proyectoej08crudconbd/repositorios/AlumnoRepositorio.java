package com.salesianostriana.dam.proyectoej08crudconbd.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.salesianostriana.dam.proyectoej08crudconbd.modelo.Alumno;

public interface AlumnoRepositorio extends JpaRepository<Alumno, Long> {

}
