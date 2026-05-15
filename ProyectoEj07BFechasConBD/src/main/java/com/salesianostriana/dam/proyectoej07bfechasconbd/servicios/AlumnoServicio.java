package com.salesianostriana.dam.proyectoej07bfechasconbd.servicios;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.proyectoej07bfechasconbd.model.Alumno;
import com.salesianostriana.dam.proyectoej07bfechasconbd.repositorios.AlumnoRepository;
import com.salesianostriana.dam.proyectoej07bfechasconbd.servicios.base.BaseServiceImpl;

/* 
 * La idea de un servicio base es que como los servicios CRUD 
 * siempre suelen ser lo mismo, es buena práctica crear 
 * un servicio base que haga esto, y dejar nuestro servicio "concreto" 
 * en otro sitio. 
 * Esta sería nuestra clase de servicio concreta (para las cosas 
 * propias de nuestra aplicación y con Alumnos), que en este caso, 
 * al no tener una lógica de negocio más allá de los CRUD está vacía, 
 * solo extiende al servicio base
 * */

@Service
public class AlumnoServicio extends BaseServiceImpl <Alumno, Long, AlumnoRepository>{
		
	//Distintos servicios, por ejemplo:
	
	@Autowired
    private AlumnoRepository repositorio;
	
	@Override
    public List<Alumno> obtenerCumpleanerosHoy() {
        LocalDate hoy = LocalDate.now();
        return repositorio.findByCumpleanios(hoy.getMonthValue(), hoy.getDayOfMonth());
    }

    @Override
    public boolean esSuCumpleanios(Alumno alumno) {
        if (alumno.getFechaNacimiento() == null) return false;
        
        LocalDate hoy = LocalDate.now();
        LocalDate cumple = alumno.getFechaNacimiento();
        
        // Comparamos mes y día
        return cumple.getMonth() == hoy.getMonth() && 
               cumple.getDayOfMonth() == hoy.getDayOfMonth();
    }
}