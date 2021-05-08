package com.tea.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="experiencias")
public class Experiencia {

	@Id
	@Column(name = "id_experiencia")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 30)
	private String titulo;

	@Column(nullable = false, length = 255)
	private String comentario;

	@Column()
	private int puntaje;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuarios", referencedColumnName = "id_usuario", nullable = false)
	@JsonManagedReference
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "instituciones", referencedColumnName = "id_institucion", nullable = true)
	@JsonManagedReference
	private Institucion institucion;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "profesional", referencedColumnName = "id_profesional", nullable = true)
	@JsonManagedReference
	private Profesional profesional;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "actividades", referencedColumnName = "id_actividad", nullable = true)
	@JsonManagedReference
	private Actividad actividad;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Institucion getInstitucion() {
		return institucion;
	}

	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}

	public Profesional getProfesional() {
		return profesional;
	}

	public void setProfesional(Profesional profesional) {
		this.profesional = profesional;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}
}
