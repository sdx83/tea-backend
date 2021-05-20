package com.tea.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tea.model.Experiencia;
import com.tea.model.ImagenesExperiencia;
import com.tea.repository.ImagenesExperienciaRepository;

@Service
@Transactional(readOnly = false)
public class ImagenesExperienciaServiceImpl implements ImagenesExperienciaService {
	

	@Autowired
	ImagenesExperienciaRepository imagenesExperienciaDAO;
	

	@Override
	public List<ImagenesExperiencia> findByExperiencia(Experiencia exp) {
		return imagenesExperienciaDAO.findByExperiencia(exp);
	}
}
