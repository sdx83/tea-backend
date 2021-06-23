package com.tea.controller;

import com.tea.model.*;
import com.tea.service.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/Experiencias")
@Api(value = "User REST Endpoint", description = "Shows the user info")
@CrossOrigin(origins = "*")
public class ExperienciaController {

	private final ExperienciaServiceImpl experienciaService;

	private final ImagenesExperienciaServiceImpl imagenesExperienciaService;

	private final ProfesionalService profesionalService;

	private final InstitucionService institucionService;

	private final ActividadService actividadService;

    @Autowired
	public ExperienciaController(ExperienciaServiceImpl experienciaService, ImagenesExperienciaServiceImpl imagenesExperienciaService,
								 ProfesionalService profesionalService, InstitucionService institucionService, ActividadService actividadService) {
		this.experienciaService = experienciaService;
		this.imagenesExperienciaService = imagenesExperienciaService;
		this.profesionalService = profesionalService;
		this.institucionService = institucionService;
		this.actividadService = actividadService;
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
 	@PostMapping()
 	public ResponseEntity<Experiencia> saveExperiencia(@RequestBody Experiencia exp) throws Exception {
 	try {
 			updateValoracionPromedioEntidad(exp);
 			return ResponseEntity.ok(experienciaService.save(exp));
 		} catch (ResponseStatusException e) {
 	 		throw new Exception(e.getReason());
 	 	} catch (Exception e) {
 	 		throw new Exception("Error al crear la experiencia");
 	 	}
 	}

	private void updateValoracionPromedioEntidad(Experiencia exp) {
		if(exp.getProfesional() != null) {
    		Optional<Profesional> profesional = profesionalService.findById(exp.getProfesional().getId());
			profesional.get().setValoracionPromedio(getValoracionPromedio(exp, profesional.get().getExperiencias()));
			profesionalService.save(profesional.get());
		}
		if(exp.getActividad() != null) {
			Optional<Actividad> actividad = actividadService.findById(exp.getActividad().getId());
			actividad.get().setValoracionPromedio(getValoracionPromedio(exp, actividad.get().getExperiencias()));
			actividadService.save(actividad.get());
		}
		if(exp.getInstitucion() != null) {
			Optional<Institucion> institucion = institucionService.findById(exp.getInstitucion().getId());
			institucion.get().setValoracionPromedio(getValoracionPromedio(exp, institucion.get().getExperiencias()));
			institucionService.save(institucion.get());
		}

	}

	private Double getValoracionPromedio(Experiencia exp, List<Experiencia> experiencias){
		int valoraciones = experiencias.stream().mapToInt(experiencia -> experiencia.getPuntaje()).sum() + exp.getPuntaje();;
		int total = experiencias.size() + 1;
		return Double.valueOf(valoraciones / total);
	}


	// POST: http://localhost:8080/Experiencias/1/uploadImages
 	@PostMapping("/{idExperiencia}/uploadImages")
 	public ResponseEntity<Void> uploadImages(@PathVariable("idExperiencia") long idExperiencia,
 					@RequestParam("image") MultipartFile[] files) throws Exception {


 		try {
 			Optional<Experiencia> experiencia;
 			experiencia = experienciaService.findById(idExperiencia);

 			if(experiencia.isPresent()) {
 				experienciaService.saveFiles(files, experiencia.get());
 	 			return null;
 	 		}else {
 	 			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No se encontró la experiencia");
 	 		}
 		} catch (ResponseStatusException e) {
			throw new Exception(e.getReason());
		} catch (Exception ex) {
			throw new Exception(ex.getCause().getMessage());
		}
 	}

	// GET: http://localhost:8080/Experiencias/{id_exp}/downloadImages
    @GetMapping("{idExperiencia}/downloadImages")
	public ResponseEntity<List<byte[]>> getImages(@PathVariable("idExperiencia") long idExperiencia) throws Exception{

 		try {
 			Optional<Experiencia> experiencia;
 			experiencia = experienciaService.findById(idExperiencia);

 			if (!experiencia.isPresent()) {
 				throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No se encontraró la experiencia");
 			}

 			List<ImagenesExperiencia> experiencias = imagenesExperienciaService.findByExperiencia(experiencia.get());

 			List<byte[]> imagenes = new ArrayList<>();

 			for (ImagenesExperiencia item : experiencias) {
 				imagenes.add(item.getImagen());
			}

 			if(experiencias.size() > 0) {
 	 			return ResponseEntity.ok(imagenes);
 	 		}else {
 	 			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No se encontraron imágenes");
 	 		}
 		} catch (ResponseStatusException e) {
			throw new Exception(e.getReason());
		} catch (Exception e) {
			throw new Exception("Error inesperado");
		}
	}
}
