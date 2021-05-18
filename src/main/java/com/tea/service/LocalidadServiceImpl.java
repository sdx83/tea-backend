package com.tea.service;

import com.tea.model.Localidad;
import com.tea.repository.LocalidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalidadServiceImpl implements LocalidadService{

    private LocalidadRepository localidadRepository;

    @Autowired
    public LocalidadServiceImpl(LocalidadRepository localidadRepository) {
        this.localidadRepository = localidadRepository;
    }

    @Override
    public List<Localidad> findAll() {
        return localidadRepository.findAll();
    }

}
