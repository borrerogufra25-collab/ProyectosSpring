package com.salesianostriana.dam.proyectoej13citas.service;


import org.springframework.stereotype.Service;

import com.salesianostriana.dam.proyectoej13citas.model.Paciente;
import com.salesianostriana.dam.proyectoej13citas.repository.PacienteRepository;
import com.salesianostriana.dam.proyectoej13citas.service.base.BaseServiceImpl;

@Service
public class PacienteService extends BaseServiceImpl<Paciente, Long, PacienteRepository> {


    // Si en el futuro necesitas un método específico (ej: buscar por alias), lo añades aquí
}