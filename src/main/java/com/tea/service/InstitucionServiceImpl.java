package com.tea.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tea.model.Institucion;
import com.tea.repository.InstitucionRepository;

@Service
@Transactional(readOnly = false)
public class InstitucionServiceImpl implements InstitucionService {
	
	@Autowired
	InstitucionRepository institucionDAO;
	
	@Transactional(readOnly = true)
	public List<Institucion> findAll() {
		
		List<Institucion> instituciones = institucionDAO.findAll();
		
		//ordeno las instituciones alfabeticamente
 		Comparator<Institucion> comparadorInstituciones = (Institucion i1, Institucion i2) -> {
 			return (i1.getNombre()).compareTo((i2.getNombre()));
 		};
 		
 		Collections.sort(instituciones, comparadorInstituciones);
		 
		return instituciones ;
	}

	@Override
	public List<Institucion> findByLocalidadAndPuntajeAndParams(Long localidad, double puntaje, String palabras) {
		return null;
	}
}
