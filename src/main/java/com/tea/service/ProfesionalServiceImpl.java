package com.tea.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.tea.model.Localidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tea.model.Profesional;
import com.tea.repository.ProfesionalRepository;

@Service
@Transactional(readOnly = false)
public class ProfesionalServiceImpl implements ProfesionalService {

	private ProfesionalRepository profesionalDAO;

	@Autowired
	public ProfesionalServiceImpl(ProfesionalRepository profesionalDAO) {
		this.profesionalDAO = profesionalDAO;
	}

	@Transactional(readOnly = true)
	public List<Profesional> findAll() {
		
		List<Profesional> profesionales = profesionalDAO.findAll();
		
		//ordeno los profesionales alfabeticamente
 		Comparator<Profesional> comparadorProfesionales = (Profesional p1, Profesional p2) -> {
 			return (p1.getApellido()).compareTo((p2.getApellido()));
 		};
 		
 		Collections.sort(profesionales, comparadorProfesionales);
		 
		return profesionales ;
	}

}
