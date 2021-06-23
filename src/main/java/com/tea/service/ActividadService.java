package com.tea.service;

import java.util.List;
import java.util.Optional;

import com.tea.model.Actividad;
import com.tea.model.InstructivoActividad;

public interface ActividadService {

	List<Actividad> findAll();

	List<Actividad> findAllOrderByValoracionPromedio();
	
    List<InstructivoActividad> findInstructivo(Actividad actividad);
	
	Optional<Actividad> findById(Long id);

    void save(Actividad actividad);
}
