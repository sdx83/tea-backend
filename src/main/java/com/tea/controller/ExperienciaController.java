package com.tea.controller;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.tea.model.Experiencia;
import com.tea.model.ImagenesExperiencia;
import com.tea.service.ExperienciaServiceImpl;
import com.tea.service.ImagenesExperienciaServiceImpl;

import io.swagger.annotations.Api;


@RestController
@RequestMapping("/Experiencias")
@Api(value = "User REST Endpoint", description = "Shows the user info")
@CrossOrigin(origins = "*")
public class ExperienciaController {

    private final ExperienciaServiceImpl experienciaService;
    
    private final ImagenesExperienciaServiceImpl imagenesExperienciaService;

    @Autowired
	public ExperienciaController(ExperienciaServiceImpl experienciaService, ImagenesExperienciaServiceImpl imagenesExperienciaService) {
		this.experienciaService = experienciaService;
		this.imagenesExperienciaService = imagenesExperienciaService;
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
 			return ResponseEntity.ok(experienciaService.save(exp));
 		} catch (ResponseStatusException e) {
 	 		throw new Exception(e.getReason());
 	 	} catch (Exception e) {
 	 		throw new Exception("Error al crear la experiencia");
 	 	}
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
