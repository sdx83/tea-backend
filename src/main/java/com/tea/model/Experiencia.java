package com.tea.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="experiencias")
public class Experiencia implements Serializable {

	private static final long serialVersionUID = 4689342591539815009L;

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

	@Column( )
	private String nickname;
	
	@Transient
	private String tipoExperiencia;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario", referencedColumnName = "id_usuario", nullable = false)
	//@JsonManagedReference
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "institucion", referencedColumnName = "id_institucion", nullable = true)
	@JsonBackReference(value="institucion")
	private Institucion institucion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "profesional", referencedColumnName = "id_profesional", nullable = true)
	@JsonBackReference(value="profesional")
	private Profesional profesional;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "actividad", referencedColumnName = "id_actividad", nullable = true)
	@JsonBackReference(value="actividad")
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
	
	public String getTipoExperiencia() {
		
		String tipoExp = "";
		if (this.getActividad() != null)
			tipoExp = "Actividad";
		if (this.getProfesional() != null)
			tipoExp =  "Profesional";
		if (this.getInstitucion() != null)
			tipoExp = "Institucion";
		
		return tipoExp;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}
