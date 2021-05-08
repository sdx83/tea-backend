package com.tea.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="profesional")
public class Profesional implements Serializable {

	private static final long serialVersionUID = -8927265276161441192L;

	@Id
    @Column(name="id_profesional")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false,length=50)
    private String nombre;

    @Column(nullable=false,length=50)
    private String apellido;

    @Column(nullable=false,length=20)
    private String matricula;

    @Column(nullable=false,length=50)
    private String especialidad;

    @Column(nullable=false,length=100)
    private String latitud;

    @Column(nullable=false,length=100)
    private String longitud;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "localidad", referencedColumnName = "id_localidad")
    private Localidad localidad;

    @OneToMany(mappedBy = "profesional", fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST)
    @JsonManagedReference
    List<Experiencia> experiencias;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
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

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public List<Experiencia> getExperiencias() {
        return experiencias;
    }

    public void setExperiencias(List<Experiencia> experiencias) {
        this.experiencias = experiencias;
    }
}
