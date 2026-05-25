package com.salesianostriana.dam.proyectoej13citas.exception;

import com.salesianostriana.dam.proyectoej13citas.model.Cita;

/** Añadir campo Cita
 * Si se está creando una cita que coincida con una que ya está, el programa falla porque GlobalExceptionControllerAdvide intenta interceptar
 * la excepción, ejecuta la línea model.getAttribute("cita"). 
 * Sin embargo, en ese momento exacto del ciclo de vida de Spring, 
 * el objeto que el usuario rellenó en el formulario aún no se ha guardado en el modelo global con ese nombre. 
 * Al recibir un null, el constructor de validación artificial de Spring colapsa y lanza el error.
 * 
 * Se podría solventar con try-catch pero lo haremos usando el método del Advice.
 * 
 * En lugar de buscar la cita a ciegas en el modelo, haremos que reciba directamente 
 * el objeto Cita erróneo pasándolo a través de la propia excepción.
 * */

public class ConflictoAgendaException extends RuntimeException {
    
    private final Cita citaErronea;

    public ConflictoAgendaException(String mensaje, Cita citaErronea) {
        super(mensaje);
        this.citaErronea = citaErronea;
    }

    public Cita getCitaErronea() {
        return citaErronea;
    }
}