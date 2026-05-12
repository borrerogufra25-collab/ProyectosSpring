package com.salesianostriana.dam.proyectoej07bfechasconbd.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.salesianostriana.dam.proyectoej07bfechasconbd.model.Alumno;

/* 
 * Solo una interfaz que extiende de JpaReporitory.
 * Esta última es una interfaz del framework Spring data
 * que contiene métodos como findAll, getOne...
 * Podéis consultar más en 
 * https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html
 * Por tanto, nuestra interfaz AlumnoRepository, hereda todos esos métodos 
 * que implementaremos en el servicio
 * y podemos usarla sin problema */

public interface AlumnoRepository extends JpaRepository <Alumno, Long>{
	
	// Consulta para obtener alumnos que cumplen años hoy (mes y día coinciden)
    @Query("SELECT a FROM Alumno a WHERE MONTH(a.fechaNacimiento) = :mes AND DAY(a.fechaNacimiento) = :dia")
    List<Alumno> findByCumpleanios(@Param("mes") int mes, @Param("dia") int dia);
}

