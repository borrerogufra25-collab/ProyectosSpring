package com.salesianostriana.dam.proyectoej07bfechasconbd.servicios.base;

import java.util.List;
import java.util.Optional;

import com.salesianostriana.dam.proyectoej07bfechasconbd.model.Alumno;


/*
 * Esta interfaz es parte de unm patrón de diseño que en busca 
 * de una mejor abstracción 
 * define un "contrato" común para todas las clases de la capa de servicio.

1. La interfaz define qué acciones se pueden realizar, pero no cómo. 

2. Sin una interfaz o clase base, tendrías que escribir las mismas firmas 
de métodos una y otra vez para cada entidad. 
Con una interfaz genérica, puedes definir los métodos estándar 
usando Genéricos de Java (<T, ID>).

3. Desacoplamiento y Mantenibilidad
En Spring Boot, los controladores (@Controller) no deberían hablar 
directamente con los repositorios. Hablan con la Interfaz del Servicio. 
Esto te permite:

Cambiar la implementación lógica sin que el controlador se entere.

Facilitar las pruebas unitarias (Unit Testing), 
permitiendo crear "mocks" (simulacros) de los servicios fácilmente.
 * 
 * En definitiva tenemos los métodos CRUD y más usados  en cuanto a 
 * servicios "generales", es decir, listarTodos, buscarPor Id, save, edit
 * y dos deletes (se podrían agregar más)
 * */
public interface BaseService<T, ID> {
	
	List<T> findAll();
	
	Optional<T> findById(ID id);
	
	T save(T t);
	
	T edit(T t);
	
	void delete(T t);
	
	void deleteById(ID id);
	
	//Uno diferente
	// Método específico para la lógica de negocio de cumpleaños
    List<Alumno> obtenerCumpleanerosHoy();
    boolean esSuCumpleanios(Alumno alumno);
	

}
