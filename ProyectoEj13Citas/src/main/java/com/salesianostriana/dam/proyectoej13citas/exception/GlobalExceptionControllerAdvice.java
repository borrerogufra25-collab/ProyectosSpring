package com.salesianostriana.dam.proyectoej13citas.exception;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.salesianostriana.dam.proyectoej13citas.model.Cita;
import com.salesianostriana.dam.proyectoej13citas.service.CirujanoService;
import com.salesianostriana.dam.proyectoej13citas.service.PacienteService;

@ControllerAdvice
public class GlobalExceptionControllerAdvice {

    private final PacienteService pacienteService;
    private final CirujanoService cirujanoService;

    // Inyectamos los servicios necesarios para repoblar el formulario en caso de error
    public GlobalExceptionControllerAdvice(PacienteService pacienteService, CirujanoService cirujanoService) {
        this.pacienteService = pacienteService;
        this.cirujanoService = cirujanoService;
    }

    /**
     * Intercepta el conflicto de agenda, inyecta el error en la interfaz del formulario
     * y recarga la vista para el usuario de manera transparente
     */
    @ExceptionHandler(ConflictoAgendaException.class)
    public String handleConflictoAgenda(ConflictoAgendaException ex, Model model) {
        // 1. Extraemos de forma 100% segura el objeto que viaja en la excepción
        Cita citaErronea = ex.getCitaErronea();
        
        // Recargamos el objeto en el modelo para que el formulario retenga los inputs
        model.addAttribute("cita", citaErronea);
        
        // 2. Vinculamos el error al campo 'cirujano'
        /*El DataBinder es un componente de Spring que se encarga
         * de conectar o vincular los datos que viajan desde una petición http con los objetos
         * de nuestra aplicación
         * 
         *  En este caso, sirve simultaneamente para simular el comportamiento
         *  de las validaciones de Spring cuando ocurre nuestra excepción personalizada
         *  conflictoAgendaException
         *  
         *  Le pasamos el objeto cita que es el que contine el error y
         *  le indicamos que el nombre de esa variable en el html es "cita"
         *  
         *  */
        
        DataBinder binder = new DataBinder(citaErronea, "cita");
        //Pedimos que se cree un BindingResult, que es un mapa (como una libreta de notas) 
        //donde Spring anota qué campos del formulario son válidos y cuáles tienen errores
        
        /*Spring general este bindingResult automáticamente cuanso usamos @valid o @validated en los parámetros de un controlador
         * para validar cosas simples como que un campo no esté vacío
         * 
         * Aquí como la comprobación de si un cirujano está ocupado requiere ir a la base de datos 
         * y hacer lógica en el servicio, no podemos usar las validaciones automáticas de Spring
         * 
         * */
        BindingResult bindingResult = binder.getBindingResult();
        
        //Aquí le decimos a la libreta "apunta un error específicamente en el campo cirujano de la cita
        //y ponle como texto explicativo el mensaje que viene dentro de la 
        //excepción (ex.getMessage())
        bindingResult.rejectValue("cirujano", "error.cita", ex.getMessage());
        
        // 3. Inyectamos el resultado de la validación en la pantalla
        model.addAttribute("org.springframework.validation.BindingResult.cita", bindingResult);
        
        // 4. Repoblamos los desplegables auxiliares para que no se vacíen
        model.addAttribute("pacientes", pacienteService.findAll());
        model.addAttribute("cirujanos", cirujanoService.findAll());
        
        // 5. Devolvemos el archivo HTML del formulario
        return "citas/formCita";
    }

    @ExceptionHandler(EntidadNoEncontradaException.class)
    public String handleEntidadNoEncontrada(EntidadNoEncontradaException ex, Model model) {
        model.addAttribute("codigoError", "404 - RECURSO NO LOCALIZADO");
        model.addAttribute("mensajeError", ex.getMessage());
        return "error/error-clinica";
    }

    @ExceptionHandler(Exception.class)
    public String handleErroresGenerales(Exception ex, Model model) {
        model.addAttribute("codigoError", "500 - FALLO EN LA MATRIZ CORTICAL");
        model.addAttribute("mensajeError", "Ha ocurrido un error inesperado en el sistema: " + ex.getMessage());
        return "error/error-clinica";
    }
}