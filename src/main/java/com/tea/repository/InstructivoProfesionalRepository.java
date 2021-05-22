package com.tea.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tea.model.InstructivoProfesional;
import com.tea.model.Profesional;

@Repository("instructivoProfesionalRepository")
public interface InstructivoProfesionalRepository extends JpaRepository<InstructivoProfesional, Long> {
	
	List<InstructivoProfesional> findByProfesional(Profesional profesional);
}
