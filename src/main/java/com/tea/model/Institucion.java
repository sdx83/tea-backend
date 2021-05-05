package com.tea.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="instituciones")
public class Institucion implements Serializable {

	private static final long serialVersionUID = 1948363616139314038L;

	@Id
	@Column(name="id_institucion")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idInstitucion;
	
	@Column(nullable=false,length=20)
	private String nombre;
	
	@Column(nullable=false,length=20)
	private String especialidad;
	
	@Column(nullable=false,length=20)
	private String direccion;
	
	@Column(nullable=false,length=20)
	private String localidad;
	
	@Column(nullable=false,length=20)
	private String provincia;
	
	@Column(nullable=false,length=100)
	private String latitud;
	
	@Column(nullable=false,length=100)
	private String longitud;

	public Long getIdInstitucion() {
		return idInstitucion;
	}

	public void setIdInstitucion(Long idInstitucion) {
		this.idInstitucion = idInstitucion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
	
}
