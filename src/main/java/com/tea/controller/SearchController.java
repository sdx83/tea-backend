package com.tea.controller;

import com.tea.model.Actividad;
import com.tea.model.Institucion;
import com.tea.model.Profesional;
import com.tea.service.ActividadService;
import com.tea.service.InstitucionService;
import com.tea.service.ProfesionalService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/search")
@Api(value = "Busqueda", description = "busqueda por profesional, actividad o institucion")
@CrossOrigin(origins = "*")
public class SearchController {

    private final ProfesionalService profesionalService;
    private final InstitucionService institucionService;
    private final ActividadService actividadService;

    @Autowired
    public SearchController(ProfesionalService profesionalService, InstitucionService institucionService, ActividadService actividadService) {
        this.profesionalService = profesionalService;
        this.institucionService = institucionService;
        this.actividadService = actividadService;
    }

    @GetMapping(value = "/{tipo}")
    public ResponseEntity<?> getUsuarioByUserAndPass(@PathVariable("tipo") Optional<String> tipoOptional) throws Exception {
        String tipo = null;
        if (tipoOptional.isPresent()) {
            tipo = tipoOptional.get();
        }
        if(tipo.equals("Profesional")){

           return new ResponseEntity<List<Profesional>>(this.profesionalService.findAll(), HttpStatus.OK);
        }
        if(tipo.equals("Institucion")){
            return new ResponseEntity<List<Institucion>>(this.institucionService.findAll(), HttpStatus.OK);
        }
        if(tipo.equals("Actividad")){
            return new ResponseEntity<List<Actividad>>(this.actividadService.findAll(), HttpStatus.OK);
        }
        return null;
    }
}