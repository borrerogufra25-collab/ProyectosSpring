package com.salesianostriana.dam.proyectoej07fechassinbd.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.salesianostriana.dam.proyectoej07fechassinbd.model.Alumno;
import lombok.NoArgsConstructor;

@Service @NoArgsConstructor
public class AlumnoServicio {

	//Como seguimos sin base de datos, hacemos el guardado en una
	//lista denbtro de este servicio y la devolvemos con el método
	//getLista para simular la BBDD.
	private List <Alumno> lista=new ArrayList <Alumno>();
	
	public void agregar (Alumno a) {
		
		lista.add(a);
	}
		
	public List<Alumno> getLista (){
		return lista; 
	}
	
}