package com.salesianostriana.dam.proyectoej07bfechasconbd.servicios.base;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

/*Si la interfaz BaseService es el "contrato" (donde decimos qué se va a hacer), 
 * la clase de implementación es el "músculo", 
 * donde realmente escribimos el código Java que hace que las cosas funcionen.

 * 
 * Esta clase base nos permite tener un envoltorio genérico para cualquier tipo
 * de repositorio que maneje una determinada entidad. De esta forma, los métodos
 * CRUD más habituales, ya los tenemos implementados, y solamente necesitaremos
 * implementar por nuestra cuenta aquellos métodos más complejos de nuestra
 * lógica de negocio.
 * 
 * Esta versión no incluye ningún mecanismo de validación para garantizar que T
 * sea una entidad.
 * 
 * Explicamos los genéricos:
 * 
 * T debe ser el tipo de entidad que se va a gestionar, en nuestro caso será
 * Alumno. 
 * ID el tipo de dato de su ID (La clave primaria de T). 
 * R es el tipo de dato del repositorio que vamos a usar dentro, 
 * en este caso AlumnoRepository.
 * 
 * Este tipo de datos R se llama "delimitado". ¿Qué significa eso?:
 * 
 * Básicamente dice, pásame un R pero lo que me pases debe extender JpaRepository <T, ID>
 * obligatoriamente. Significa que R debe ser de un tipo que extienda (herede) a
 * JPARepository <T, ID> por lo que no puede ser de cualquier tipo que queramos,
 * sino que debe cumplir esta condición, de ahí el que se le llame delimitado,
 * pues estamos "delimitando" el tipo de objetos que se pueden usar a aquellos que
 * cumplan esto.
 * 
 * Una de las limitaciones que tiene es fácilmente subsanable. Se podría dar el
 * caso de que el tipo T no fuese una entidad de nuestro modelo. Para
 * solucionarlo, podríamos forzar a que todas nuestras clases modelo
 * implementaran una interfaz, y utilizar dicha interfaz para delimitar el tipo
 * del parámetro T.
 * 
 * Por último, implementa la interfaz.
*/
public abstract class BaseServiceImpl<T, ID, R extends JpaRepository<T, ID>> implements BaseService<T, ID> {

	//Inyectamos el objeto del repositorio
	@Autowired
	protected R repository;

	@Override
	public List<T> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<T> findById(ID id) {
		return repository.findById(id);
	}

	@Override
	public T save(T t) {
		return repository.save(t);
	}

	@Override
	public T edit(T t) {
		return repository.save(t);
	}

	@Override
	public void delete(T t) {
		repository.delete(t);

	}

	@Override
	public void deleteById(ID id) {
		repository.deleteById(id);
	}
}
