package com.tea.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="experiencias")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Experiencia implements Serializable {

	private static final long serialVersionUID = -7546542607087285227L;

	@Id
	@Column(name="id_experiencia")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idExperiencia;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = false)
	private Usuario usuario;
	
	
	@Column(nullable=false,length=255)
	private String comentario;
	
	@Column(nullable=false)
	private int puntaje;
	
	public Long getIdExperiencia() {
		return idExperiencia;
	}

	public void setIdExperiencia(Long idExperiencia) {
		this.idExperiencia = idExperiencia;
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
	
	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
}
