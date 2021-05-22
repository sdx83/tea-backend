package com.tea.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tea.model.Institucion;
import com.tea.model.InstructivoInstitucion;

@Repository("instructivoInstitucionRepository")
public interface InstructivoInstitucionRepository extends JpaRepository<InstructivoInstitucion, Long> {
	
	List<InstructivoInstitucion> findByInstitucion(Institucion institucion);
}
