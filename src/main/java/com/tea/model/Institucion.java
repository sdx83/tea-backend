package com.tea.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="instituciones")
public class Institucion implements Serializable {

	private static final long serialVersionUID = -973596195707393069L;

	@Id
    @Column(name="id_institucion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false,length=50)
    private String nombre;

    @Column(nullable=false,length=50)
    private String direccion;

    @Column(nullable=false,length=50)
    private String especialidad;

    @Column()
    private String telefono;

    @Column()
    private String email;

    @Column(nullable=false,length=100)
    private String latitud;

    @Column(nullable=false,length=100)
    private String longitud;

    @Column()
    private String entidad;

    @Column(nullable=false)
    private Double valoracionPromedio;

    @Column(name = "imagen")
    private String imagen;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "localidad", referencedColumnName = "id_localidad")
    private Localidad localidad;

    @OneToMany(mappedBy = "institucion", fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST)
    @JsonManagedReference(value="institucion")
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public Double getValoracionPromedio() {
        return valoracionPromedio;
    }

    public void setValoracionPromedio(Double valoracionPromedio) {
        this.valoracionPromedio = valoracionPromedio;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
