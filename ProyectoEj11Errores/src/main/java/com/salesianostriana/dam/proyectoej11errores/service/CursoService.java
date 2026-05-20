package com.salesianostriana.dam.proyectoej11errores.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.proyectoej11errores.exceptions.SinPlazasDisponiblesException;
import com.salesianostriana.dam.proyectoej11errores.model.Curso;
import com.salesianostriana.dam.proyectoej11errores.repository.CursoRepository;

@Service
public class CursoService {

    @Autowired
    private CursoRepository repository;

    public List<Curso> listarTodos() {
        return repository.findAll();
    }

    public Curso guardar(Curso curso) {
        // Uso de excepción del API de Java (Uso preventivo)
        if (curso.getPlazasMaximas() != null && curso.getPlazasMaximas() > 50) {
            throw new IllegalArgumentException("Por normativa del centro, no se permiten cursos de más de 50 alumnos.");
        }
        return repository.save(curso);
    }

    //Deberíamos devolver un Optional
    public Curso buscarPorId(Long id) {
        // Si no se encuentra, lanza una excepción nativa de Java (NoSuchElementException)
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException());
    }

    public void matricularAlumno(Long cursoId) {
        Curso curso = buscarPorId(cursoId);
        
        // Uso de nuestra excepción personalizada de negocio
        if (curso.getPlazasOcupadas() >= curso.getPlazasMaximas()) {
            throw new SinPlazasDisponiblesException("No se puede matricular. El curso '" + curso.getNombre() + "' ya está completo.");
        }       
        curso.setPlazasOcupadas(curso.getPlazasOcupadas() + 1);
        repository.save(curso);
    }
}