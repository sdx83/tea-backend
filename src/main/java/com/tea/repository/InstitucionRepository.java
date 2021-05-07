package com.tea.repository;

import com.tea.model.Institucion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("institucionRepository")
public interface InstitucionRepository extends JpaRepository<Institucion, Long> {
}
