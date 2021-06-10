package com.tea.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tea.model.Experiencia;
import com.tea.model.Institucion;
import com.tea.model.InstructivoInstitucion;
import com.tea.service.EmailServiceImpl;
import com.tea.service.InstitucionServiceImpl;
import com.tea.utils.InstitucionMailHelper;
import com.tea.utils.ProfesionalMailHelper;

import io.swagger.annotations.Api;


@RestController
@RequestMapping("/Instituciones")
@Api(value = "User REST Endpoint", description = "Shows the user info")
@CrossOrigin(origins = "*")
public class InstitucionController {

    private final InstitucionServiceImpl institucionService;

    private final EmailServiceImpl mailService;
    
    @Autowired
	public InstitucionController(InstitucionServiceImpl institucionService, EmailServiceImpl mailService) {
		this.institucionService = institucionService;
		this.mailService = mailService;
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

	// GET: http://localhost:8080/Instituciones/id/Instructivo
    @GetMapping("/{idInstitucion}/Instructivo")
	public ResponseEntity<List<InstructivoInstitucion>> getInstructivo
											(@PathVariable("idInstitucion") long idInstitucion) throws Exception{

 		try {
 			
 			Optional<Institucion> institucion = institucionService.findById(idInstitucion);
 			
 			if(!institucion.isPresent()) {
 				throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No se encontró la institución");
 			}
 			
 			List<InstructivoInstitucion> instructivos = institucionService.findInstructivo(institucion.get());

 			if(instructivos.size() > 0) {
 	 			return ResponseEntity.ok(instructivos);
 	 		}else {
 	 			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No se encontraron instructivos");
 	 		}
 		} catch (ResponseStatusException e) {
			throw new Exception(e.getReason());
		} catch (Exception e) {
			throw new Exception("Error inesperado");
		}
	}

	@GetMapping("/{idInstitucion}")
	public ResponseEntity<Institucion> getActividadById
			(@PathVariable("idInstitucion") long idInstitucion) throws Exception{
		try {
			Optional<Institucion> institucion = institucionService.findById(idInstitucion);

			if(!institucion.isPresent()) {
				throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No se encontró la actividad");
			}

			return ResponseEntity.ok(institucion.get());
		} catch (ResponseStatusException e) {
			throw new Exception(e.getReason());
		} catch (Exception e) {
			throw new Exception("Error inesperado");
		}
	}

	@GetMapping("/experiencias/{idInstitucion}")
	public ResponseEntity<List<Experiencia>> getExperienciasByInstitucion
			(@PathVariable("idInstitucion") long idInstitucion) throws Exception{

		try {

			Optional<Institucion> institucion = institucionService.findById(idInstitucion);

			if(!institucion.isPresent()) {
				throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No se encontró la institución");
			}

			if(institucion.get().getExperiencias().size() > 0) {
				return ResponseEntity.ok(institucion.get().getExperiencias());
			}else {
				throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No se encontraron experiencias");
			}
		} catch (ResponseStatusException e) {
			throw new Exception(e.getReason());
		} catch (Exception e) {
			throw new Exception("Error inesperado");
		}
	}
	
 	// POST: http://localhost:8080/Instituciones/Alta/Mail
 	@PostMapping("/Alta/Mail")
 	public ResponseEntity<Void> enviarMail(@RequestBody InstitucionMailHelper institucion) throws Exception {
 	
 		try {
 			
 			mailService.armarMailInstitucion(institucion);
 			
 			return ResponseEntity.ok(null);
 			
 		} catch (ResponseStatusException e) {
 	 		throw new Exception(e.getReason());
 	 	} catch (Exception e) {
 	 		throw new Exception("Error al enviar mail de solicitud de alta");
 	 	}
 	}
}
