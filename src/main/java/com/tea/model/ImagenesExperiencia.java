package com.tea.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name="imagenes_experiencias")
@NoArgsConstructor
public class ImagenesExperiencia {

    @Id
    @Column(name="id_imagen")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "imagen")
    @Lob
    private byte[] imagen;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "experiencia", referencedColumnName = "id")
    private Experiencia experiencia;
}
