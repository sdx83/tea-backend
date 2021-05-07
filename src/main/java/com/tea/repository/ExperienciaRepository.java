package com.tea.repository;

import com.tea.model.Experiencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("experienciaRepository")
public interface ExperienciaRepository extends JpaRepository<Experiencia, Long> {
}
