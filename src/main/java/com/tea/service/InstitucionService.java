package com.tea.service;

import com.tea.model.Institucion;

import java.util.List;

public interface InstitucionService {

	List<Institucion> findAll();

	List<Institucion> findAllOrderByValoracionPromedio();
}
