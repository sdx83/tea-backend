package com.tea.service;

import com.tea.model.Localidad;

import java.util.List;
import java.util.Optional;

public interface LocalidadService {

    List<Localidad> findAll();

    Localidad findById(Long id);
}
