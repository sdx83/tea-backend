package com.tea.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tea.model.Actividad;
import com.tea.repository.ActividadRepository;

@Service
@Transactional(readOnly = false)
public class ActividadServiceImpl implements ActividadService {
	
	@Autowired
	ActividadRepository actividadDAO;
	
	@Transactional(readOnly = true)
	public List<Actividad> findAll() {
		
		List<Actividad> actividades = actividadDAO.findAll();
		
		//ordeno las actividades alfabeticamente
 		Comparator<Actividad> comparadorActividades = (Actividad a1, Actividad a2) -> {
 			return (a1.getDescripcion()).compareTo((a2.getDescripcion()));
 		};
 		
 		Collections.sort(actividades, comparadorActividades);
		 
		return actividades ;
	}
}
