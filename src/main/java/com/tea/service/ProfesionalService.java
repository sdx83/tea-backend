package com.tea.service;

import java.util.List;
import java.util.Optional;

import com.tea.model.Profesional;
import org.springframework.http.HttpStatus;

public interface ProfesionalService {

	List<Profesional> findAll();

}
