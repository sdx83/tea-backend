package com.tea.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tea.model.Experiencia;
import com.tea.model.ImagenesExperiencia;

@Repository("imagenesExperienciaRepository")
public interface ImagenesExperienciaRepository extends JpaRepository<ImagenesExperiencia, Long> {
	
	List<ImagenesExperiencia> findByExperiencia(Experiencia exp);
}
