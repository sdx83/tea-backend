package com.tea.service;

import java.util.List;

import com.tea.model.Experiencia;

public interface ExperienciaService {

	List<Experiencia> findAll();
	
	public Experiencia save(Experiencia experiencia);
}
