package com.salesianostriana.dam.proyectoej13citas.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.proyectoej13citas.exception.ConflictoAgendaException;
import com.salesianostriana.dam.proyectoej13citas.model.Cita;
import com.salesianostriana.dam.proyectoej13citas.repository.CitaRepository;
import com.salesianostriana.dam.proyectoej13citas.service.base.BaseServiceImpl;

@Service
public class CitaService extends BaseServiceImpl<Cita, Long, CitaRepository> {

	//Duración estipuladoa mínima (por poner algo)
    private static final long DURACION_OPERACION_HORAS = 2;

    
    /*La regla matemática universal para saber si dos eventos se solapan es:
     * Inicio_A < Fin_B and Fin_A > Inicio_B
     * 
     */
    private boolean seSolapan(LocalDateTime inicioA, LocalDateTime finA, LocalDateTime inicioB, LocalDateTime finB) {
        return inicioA.isBefore(finB) && finA.isAfter(inicioB);
    }

    /*Método para comprobar si dos citas tiene conflicto horario:
     * */
    public boolean tieneConflictoHorario(Long cirujanoId, LocalDateTime inicioNuevaCita) {
    	//Si estamos creando una cita nueva debemos sumar 2 hporas
    	//a la que se elija para esta cita que es lo que tarda
        LocalDateTime finNuevaCita = inicioNuevaCita.plusHours(DURACION_OPERACION_HORAS);
        
        //Traemos todas las citas (hay formas más eficientes)
        List<Cita> todasLasCitas = this.findAll();

        /*Creamos el stream:
         * - filtramos citas por cirujano (puede haber citas a la misma hora y día
         * pero no el mismo cirujano)
         * - Buscamos cualquiera que cumpla la condición del método de arriba, es decir, 
         * que se solapen
         * */
        return todasLasCitas.stream()
                .filter(cita -> cita.getCirujano() != null && cita.getCirujano().getId().equals(cirujanoId))
                .anyMatch(citaExistente -> {
                    LocalDateTime inicioExistente = citaExistente.getFechaHora();
                    LocalDateTime finExistente = inicioExistente.plusHours(DURACION_OPERACION_HORAS);
                    return seSolapan(inicioNuevaCita, finNuevaCita, inicioExistente, finExistente);
                });
    }

    //Si la cita tiene conflicto horario lanzamos excepción conflicto de agenda, si no
    //agregamos la nueva cita
    public Cita registrarCita(Cita nuevaCita) {
        if (tieneConflictoHorario(nuevaCita.getCirujano().getId(), nuevaCita.getFechaHora())) {
            throw new ConflictoAgendaException("El Cirujano seleccionado ya está asignado a otra intervención en ese bloque horario.", nuevaCita);
        }
        return this.save(nuevaCita);
    }
}