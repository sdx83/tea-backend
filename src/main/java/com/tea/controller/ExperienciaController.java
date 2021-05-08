package com.tea.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tea.model.Experiencia;
import com.tea.service.ExperienciaServiceImpl;

import io.swagger.annotations.Api;


@RestController
@RequestMapping("/Experiencias")
@Api(value = "User REST Endpoint", description = "Shows the user info")
@CrossOrigin(origins = "*")
public class ExperienciaController {

    private final ExperienciaServiceImpl experienciaService;

    @Autowired
	public ExperienciaController(ExperienciaServiceImpl experienciaService) {
		this.experienciaService = experienciaService;
	}

	// GET: http://localhost:8080/Experiencias/
    @GetMapping()
	public ResponseEntity<List<Experiencia>> getAll() throws Exception{

 		try {
 			List<Experiencia> experiencias = experienciaService.findAll();

 			if(experiencias.size() > 0) {
 	 			return ResponseEntity.ok(experiencias);
 	 		}else {
 	 			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No se encontraron experiencias");
 	 		}
 		} catch (ResponseStatusException e) {
			throw new Exception(e.getReason());
		} catch (Exception e) {
			throw new Exception("Error inesperado");
		}
	}
    
  	// POST: http://localhost:8080/Experiencias
 	@PostMapping
 	public ResponseEntity<Experiencia> saveExperiencia(@RequestBody Experiencia exp) throws Exception {

 		try {
 			return ResponseEntity.ok(experienciaService.save(exp));
 		} catch (ResponseStatusException e) {
 	 		throw new Exception(e.getReason());
 	 	} catch (Exception e) {
 	 		throw new Exception("Error al crear la experiencia");
 	 	}
 	}
}
