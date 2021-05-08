package com.tea.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tea.model.Experiencia;
import com.tea.repository.ExperienciaRepository;

@Service
@Transactional(readOnly = false)
public class ExperienciaServiceImpl implements ExperienciaService {
	
	@Autowired
	ExperienciaRepository experienciaDAO;
	
	@Transactional(readOnly = true)
	public List<Experiencia> findAll() {
		
		List<Experiencia> experiencias = experienciaDAO.findAll();
		
		return experiencias ;
	}
	
	@Override
	public Experiencia save(Experiencia exp) {
		
		return experienciaDAO.save(exp);
	}
}
