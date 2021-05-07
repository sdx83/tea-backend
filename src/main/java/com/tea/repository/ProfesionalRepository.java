package com.tea.repository;

import com.tea.model.Profesional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("profesionalRepository")
public interface ProfesionalRepository extends JpaRepository<Profesional, Long> {
}
