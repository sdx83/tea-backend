package com.tea.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="experiencias_profesional")
@PrimaryKeyJoinColumn(name="id_experiencia")
public class ExperienciaProfesional extends Experiencia {

	private static final long serialVersionUID = 4850848551271614344L;

	@ManyToOne
	@JoinColumn(name = "id_profesional", nullable = false)
	private Profesional profesional;

	public Profesional getProfesional() {
		return profesional;
	}

	public void setProfesional(Profesional profesional) {
		this.profesional = profesional;
	}
}
