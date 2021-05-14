package com.tea.service;

import java.util.List;

import com.tea.model.Profesional;
import org.springframework.http.HttpStatus;

public interface ProfesionalService {

	List<Profesional> findAll();

    List<Profesional> findByLocalidadAndPuntajeAndParams(Long localidad, double puntaje, String palabras);
}
