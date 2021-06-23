package com.tea.service;

import java.util.List;
import java.util.Optional;

import com.tea.model.InstructivoProfesional;
import com.tea.model.Profesional;

public interface ProfesionalService {

	List<Profesional> findAll();

	List<Profesional> findAllOrderByValoracionPromedio();
	
    List<InstructivoProfesional> findInstructivo(Profesional profesioanl);
	
	Optional<Profesional> findById(Long id);

	void save(Profesional profesional);

}
