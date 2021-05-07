package com.tea.repository;

import com.tea.model.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("actividadRepository")
public interface ActividadRepository extends JpaRepository<Actividad, Long> {
}
