package com.tea.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.tea.model.Experiencia;

public interface ExperienciaService {

	List<Experiencia> findAll();
	
	public Experiencia save(Experiencia experiencia);
	
	public void saveFiles(MultipartFile[] files, Experiencia exp) throws IOException;
}
