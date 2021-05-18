package com.tea.controller;

import com.tea.model.Institucion;
import com.tea.model.Profesional;
import com.tea.service.InstitucionService;
import com.tea.service.ProfesionalService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
@Api(value = "Busqueda", description = "busqueda por profesional, actividad o institucion")
@CrossOrigin(origins = "*")
public class SearchController {

    private final ProfesionalService profesionalService;
    private final InstitucionService institucionService;

    @Autowired
    public SearchController(ProfesionalService profesionalService, InstitucionService institucionService) {
        this.profesionalService = profesionalService;
        this.institucionService = institucionService;
    }

    @GetMapping(value = "/{tipo}/{localidad}/{puntaje}/{palabras}")
    public ResponseEntity<?> getUsuarioByUserAndPass(@PathVariable("tipo") String tipo, @PathVariable("localidad") Long localidad,
                                                     @PathVariable("puntaje") double puntaje, @PathVariable("palabras") String palabras) throws Exception {
        if(tipo.equals("Profesional")){
           return new ResponseEntity<List<Profesional>>(this.profesionalService.findByLocalidadAndPuntajeAndParams(localidad,puntaje, palabras), HttpStatus.OK);
        }
        if(tipo.equals("Institucion")){
            return new ResponseEntity<List<Institucion>>(this.institucionService.findByLocalidadAndPuntajeAndParams(localidad,puntaje, palabras), HttpStatus.OK);
        }
        return null;
    }
}