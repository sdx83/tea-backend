package com.tea.service;

import java.util.List;

import com.tea.model.Experiencia;
import com.tea.model.ImagenesExperiencia;

public interface ImagenesExperienciaService {

	List<ImagenesExperiencia> findByExperiencia(Experiencia exp);

}
