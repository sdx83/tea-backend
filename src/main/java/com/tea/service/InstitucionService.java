package com.tea.service;

import java.util.List;

import com.tea.model.Institucion;

public interface InstitucionService {

	List<Institucion> findAll();

    List<Institucion> findByLocalidadAndPuntajeAndParams(Long localidad, double puntaje, String palabras);
}
