package com.tea.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tea.model.Institucion;
import com.tea.model.InstructivoInstitucion;
import com.tea.repository.InstructivoInstitucionRepository;

@Service
@Transactional(readOnly = false)
public class InstructivoInstitucionServiceImpl implements InstructivoInstitucionService {
	

	@Autowired
	InstructivoInstitucionRepository instructivoInstitucionRepository;
	

	@Override
	public List<InstructivoInstitucion> findByInstitucion(Institucion institucion) {
		
		return instructivoInstitucionRepository.findByInstitucion(institucion);
	}
}
