package com.tea.controller;

import com.tea.model.Actividad;
import com.tea.model.Experiencia;
import com.tea.model.InstructivoActividad;
import com.tea.service.ActividadServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/Actividades")
@Api(value = "User REST Endpoint", description = "Shows the user info")
@CrossOrigin(origins = "*")
public class ActividadController {

    private final ActividadServiceImpl actividadService;

    @Autowired
	public ActividadController(ActividadServiceImpl actividadService) {
		this.actividadService = actividadService;
	}

	// GET: http://localhost:8080/Actividades/
    @GetMapping()
	public ResponseEntity<List<Actividad>> getAll() throws Exception{

 		try {
 			List<Actividad> actividades = actividadService.findAll();

 			if(actividades.size() > 0) {
 	 			return ResponseEntity.ok(actividades);
 	 		}else {
 	 			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No se encontraron actividades");
 	 		}
 		} catch (ResponseStatusException e) {
			throw new Exception(e.getReason());
		} catch (Exception e) {
			throw new Exception("Error inesperado");
		}
	}
    
	// GET: http://localhost:8080/Actividades/id/Instructivo
    @GetMapping("/{idActividad}/Instructivo")
	public ResponseEntity<List<InstructivoActividad>> getInstructivo
											(@PathVariable("idActividad") long idActividad) throws Exception{

 		try {
 			
 			Optional<Actividad> actividad = actividadService.findById(idActividad);
 			
 			if(!actividad.isPresent()) {
 				throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No se encontró la actividad");
 			}
 			
 			List<InstructivoActividad> instructivos = actividadService.findInstructivo(actividad.get());

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

	@GetMapping("/{idActividad}")
	public ResponseEntity<Actividad> getActividadById
			(@PathVariable("idActividad") long idActividad) throws Exception{
		try {
			Optional<Actividad> actividad = actividadService.findById(idActividad);

			if(!actividad.isPresent()) {
				throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No se encontró la actividad");
			}

			return ResponseEntity.ok(actividad.get());
		} catch (ResponseStatusException e) {
			throw new Exception(e.getReason());
		} catch (Exception e) {
			throw new Exception("Error inesperado");
		}
	}

	@GetMapping("/experiencias/{idActividad}")
	public ResponseEntity<List<Experiencia>> getExperienciasByInstitucion
			(@PathVariable("idActividad") long idActividad) throws Exception{

		try {

			Optional<Actividad> actividad = actividadService.findById(idActividad);

			if(!actividad.isPresent()) {
				throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No se encontró la actividad");
			}

			if(actividad.get().getExperiencias().size() > 0) {
				return ResponseEntity.ok(actividad.get().getExperiencias());
			}else {
				throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No se encontraron experiencias");
			}
		} catch (ResponseStatusException e) {
			throw new Exception(e.getReason());
		} catch (Exception e) {
			throw new Exception("Error inesperado");
		}
	}

}
