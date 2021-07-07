package com.tea.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.tea.model.Experiencia;
import com.tea.model.ImagenesExperiencia;
import com.tea.repository.ExperienciaRepository;
import com.tea.repository.ImagenesExperienciaRepository;

@Service
@Transactional(readOnly = false)
public class ExperienciaServiceImpl implements ExperienciaService {
	
	@Autowired
	ExperienciaRepository experienciaDAO;
	
	@Autowired
	ImagenesExperienciaRepository imagenesExperienciaDAO;
	
	@Transactional(readOnly = true)
	public List<Experiencia> findAll() {
		
		List<Experiencia> experiencias = experienciaDAO.findAll();
		
		return experiencias ;
	}
	
	@Override
	public Experiencia save(Experiencia exp) {
		return experienciaDAO.save(exp);
	}
	
	@Transactional(readOnly = true)
	public Optional<Experiencia> findById(Long id) {
		return experienciaDAO.findById(id);
	}
	
	@Override
	public void saveFiles(MultipartFile[] files, Experiencia exp) throws IOException {
		
		for (MultipartFile multipartFile : files) {
			ImagenesExperiencia img = new ImagenesExperiencia();
			img.setExperiencia(exp);
			img.setImagen(multipartFile.getBytes());
			imagenesExperienciaDAO.save(img);
		}
		
		return;
	}
}
