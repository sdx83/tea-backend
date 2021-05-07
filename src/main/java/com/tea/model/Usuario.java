package com.tea.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name="usuarios")
@NoArgsConstructor
public class Usuario implements Serializable  {

	private static final long serialVersionUID = -3359031128100969764L;

	@Id
	@Column(name="id_usuario")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuario;

	@Column(nullable=false,length=15)
	private String nombre;

	@Column(nullable=false,length=15)
	private String apellido;

	@Column(nullable=false, length=20, unique=true)
	private String usuario;

	@Column(nullable=false, length=20)
	private String password;

	@Column(nullable=false, length=30)
	private String mail;

	@Column(nullable=true, length=20)
	private String telefono;

	@Column(nullable = false)
	private boolean enable;

	@Column(nullable=false, length=15)
	private String nroDocumento;

	@Column(nullable=true, length=100)
	private String direccion;

	@Column(nullable=true, length=50)
	private String localidad;

	@Column(nullable=true, length=50)
	private String provincia;

	@OneToMany(mappedBy = "experiencia", fetch = FetchType.LAZY,
			cascade = CascadeType.PERSIST)
	@JsonBackReference
	List<Experiencia> experiencias;

}
