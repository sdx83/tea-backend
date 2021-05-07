package com.tea.repository;

import com.tea.model.Localidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("localidadRepository")
public interface LocalidadRepository extends JpaRepository<Localidad, Long> {
}
