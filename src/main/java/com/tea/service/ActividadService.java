package com.tea.service;

import java.util.List;

import com.tea.model.Actividad;
import com.tea.model.Institucion;

public interface ActividadService {

	List<Actividad> findAll();

	List<Actividad> findAllOrderByValoracionPromedio();
}
