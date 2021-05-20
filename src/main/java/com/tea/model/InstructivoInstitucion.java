package com.tea.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="instructivo_instituciones")
public class InstructivoInstitucion {

    @Id
    @Column(name="id_instructivo_institucion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(nullable=false,length=15)
	private String tituloEscena;
	
	@Column(nullable=false,length=50)
	private String descripcion;
	
	@Column(nullable=false,length=255)
	private String imagen;
	
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_institucion", referencedColumnName = "id_institucion",nullable = false)
    private Institucion institucion;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTituloEscena() {
		return tituloEscena;
	}

	public void setTituloEscena(String tituloEscena) {
		this.tituloEscena = tituloEscena;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Institucion getInstitucion() {
		return institucion;
	}

	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}
}
