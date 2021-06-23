package com.tea.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tea.model.Actividad;
import com.tea.model.InstructivoActividad;
import com.tea.repository.ActividadRepository;
import com.tea.repository.InstructivoActividadRepository;

@Service
@Transactional(readOnly = false)
public class ActividadServiceImpl implements ActividadService {
	
	@Autowired
	ActividadRepository actividadDAO;
	
	@Autowired
	InstructivoActividadRepository instructivoActividadDAO;
	
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

	@Override
	public List<Actividad> findAllOrderByValoracionPromedio() {
		List<Actividad> actividades = actividadDAO.findAll();

		actividades.sort(Comparator.comparingDouble(Actividad::getValoracionPromedio)
				.reversed());

		return actividades ;
	}
	
	@Override
	public List<InstructivoActividad> findInstructivo(Actividad actividad) {
		List<InstructivoActividad> instructivos = instructivoActividadDAO.findByActividad(actividad);

		return instructivos;
	}

	@Transactional(readOnly = true)
	public Optional<Actividad> findById(Long id) {
		Optional<Actividad> actividad = actividadDAO.findById(id);
		return actividad;
	}

	@Override
	public void save(Actividad actividad) {
		actividadDAO.save(actividad);
	}
}
