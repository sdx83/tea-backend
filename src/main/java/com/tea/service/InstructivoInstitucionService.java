package com.tea.service;

import java.util.List;

import com.tea.model.Institucion;
import com.tea.model.InstructivoInstitucion;

public interface InstructivoInstitucionService {

	List<InstructivoInstitucion> findByInstitucion(Institucion institucion);

}
