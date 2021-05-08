package com.tea.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tea.model.Profesional;
import com.tea.service.ProfesionalServiceImpl;

import io.swagger.annotations.Api;


@RestController
@RequestMapping("/Profesionales")
@Api(value = "User REST Endpoint", description = "Shows the user info")
@CrossOrigin(origins = "*")
public class ProfesionalController {

    private final ProfesionalServiceImpl profesionalService;

    @Autowired
	public ProfesionalController(ProfesionalServiceImpl profesionalService) {
		this.profesionalService = profesionalService;
	}

	// GET: http://localhost:8080/Profesionales/
    @GetMapping()
	public ResponseEntity<List<Profesional>> getAll() throws Exception{

 		try {
 			List<Profesional> profesionales = profesionalService.findAll();

 			if(profesionales.size() > 0) {
 	 			return ResponseEntity.ok(profesionales);
 	 		}else {
 	 			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No se encontraron profesionales");
 	 		}
 		} catch (ResponseStatusException e) {
			throw new Exception(e.getReason());
		} catch (Exception e) {
			throw new Exception("Error inesperado");
		}
	}
}
