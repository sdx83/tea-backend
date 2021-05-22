package com.tea.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tea.model.Actividad;
import com.tea.model.InstructivoActividad;

@Repository("instructivoActividadRepository")
public interface InstructivoActividadRepository extends JpaRepository<InstructivoActividad, Long> {
	
	List<InstructivoActividad> findByActividad(Actividad actividad);
}
