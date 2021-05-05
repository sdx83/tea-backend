package com.tea.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="profesionales")
public class Profesional implements Serializable {

	private static final long serialVersionUID = -805909798258967477L;
	
	@Id
	@Column(name="id_profesional")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProfesional;
	
	@Column(nullable=false,length=20)
	private String nombre;
	
	@Column(nullable=false,length=20)
	private String apellido;
	
	@Column(nullable=false,length=20)
	private String especialidad;
	
	@Column(nullable=false,length=10)
	private String matricula;
	
	public Long getIdProfesional() {
		return idProfesional;
	}

	public void setIdProfesional(Long idProfesional) {
		this.idProfesional = idProfesional;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

}
