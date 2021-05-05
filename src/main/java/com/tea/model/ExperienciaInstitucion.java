package com.tea.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="experiencias_institucion")
@PrimaryKeyJoinColumn(name="id_experiencia")
public class ExperienciaInstitucion extends Experiencia {

	private static final long serialVersionUID = 7749294115013364086L;

	@ManyToOne
	@JoinColumn(name = "id_institucion", nullable = false)
	private Institucion institucion;

	public Institucion getInstitucion() {
		return institucion;
	}

	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}
}
