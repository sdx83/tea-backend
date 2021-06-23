package com.tea.service;

import java.util.List;
import java.util.Optional;

import com.tea.model.Institucion;
import com.tea.model.InstructivoInstitucion;

public interface InstitucionService {

	List<Institucion> findAll();

	List<Institucion> findAllOrderByValoracionPromedio();
	
	List<InstructivoInstitucion> findInstructivo(Institucion institucion);
	
	Optional<Institucion> findById(Long id);

    void save(Institucion institucion);
}
