package com.tea.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tea.model.InstructivoProfesional;
import com.tea.model.Profesional;
import com.tea.repository.InstructivoProfesionalRepository;
import com.tea.repository.ProfesionalRepository;

@Service
@Transactional(readOnly = false)
public class ProfesionalServiceImpl implements ProfesionalService {

	private ProfesionalRepository profesionalDAO;
	
	@Autowired
	InstructivoProfesionalRepository instructivoProfesionalDAO;

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

	@Transactional(readOnly = true)
	public List<Profesional> findAllOrderByValoracionPromedio() {

		List<Profesional> profesionales = profesionalDAO.findAll();

		profesionales.sort(Comparator.comparingDouble(Profesional::getValoracionPromedio)
				.reversed());

		return profesionales ;
	}
	
	@Override
	public List<InstructivoProfesional> findInstructivo(Profesional profesional) {
		List<InstructivoProfesional> instructivos = instructivoProfesionalDAO.findByProfesional(profesional);

		return instructivos;
	}

	@Transactional(readOnly = true)
	public Optional<Profesional> findById(Long id) {
		Optional<Profesional> profesional = profesionalDAO.findById(id);
		return profesional;
	}
}
