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

import com.tea.model.Institucion;
import com.tea.service.InstitucionServiceImpl;

import io.swagger.annotations.Api;


@RestController
@RequestMapping("/Instituciones")
@Api(value = "User REST Endpoint", description = "Shows the user info")
@CrossOrigin(origins = "*")
public class InstitucionController {

    private final InstitucionServiceImpl institucionService;

    @Autowired
	public InstitucionController(InstitucionServiceImpl institucionService) {
		this.institucionService = institucionService;
	}

	// GET: http://localhost:8080/Instituciones/
    @GetMapping()
	public ResponseEntity<List<Institucion>> getAll() throws Exception{

 		try {
 			List<Institucion> instituciones = institucionService.findAll();

 			if(instituciones.size() > 0) {
 	 			return ResponseEntity.ok(instituciones);
 	 		}else {
 	 			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No se encontraron instituciones");
 	 		}
 		} catch (ResponseStatusException e) {
			throw new Exception(e.getReason());
		} catch (Exception e) {
			throw new Exception("Error inesperado");
		}
	}
}
