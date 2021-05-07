package com.tea.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name="experiencias")
@NoArgsConstructor
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

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "experiencia", referencedColumnName = "id", nullable = false)
	@JsonManagedReference
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "experiencia", referencedColumnName = "id", nullable = false)
	@JsonBackReference
	private Institucion institucion;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "experiencia", referencedColumnName = "id", nullable = false)
	@JsonBackReference
	private Profesional profesional;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "experiencia", referencedColumnName = "id", nullable = false)
	@JsonBackReference
	private Actividad actividad;

}
