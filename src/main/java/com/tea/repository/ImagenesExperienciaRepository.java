package com.tea.repository;

import com.tea.model.ImagenesExperiencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("imagenesExperienciaRepository")
public interface ImagenesExperienciaRepository extends JpaRepository<ImagenesExperiencia, Long> {
}
