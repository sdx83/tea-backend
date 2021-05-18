package com.tea.controller;

import com.tea.model.Localidad;
import com.tea.service.LocalidadService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/localidades")
@Api(value = "Localidades REST Endpoint", description = "listado de localidades")
@CrossOrigin(origins = "*")
public class LocalidadController {

    private final LocalidadService localidadService;

    @Autowired
    public LocalidadController(LocalidadService localidadService) {
        this.localidadService = localidadService;
    }

    @GetMapping()
    public ResponseEntity<List<Localidad>> getAll() throws Exception{

        try {
            List<Localidad> localidades = localidadService.findAll();

            if(localidades.size() > 0) {
                return ResponseEntity.ok(localidades);
            }else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No se encontraron localidades");
            }
        } catch (ResponseStatusException e) {
            throw new Exception(e.getReason());
        } catch (Exception e) {
            throw new Exception("Error inesperado");
        }
    }


}
