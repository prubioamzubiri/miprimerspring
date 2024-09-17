package com.zubiri.miprimerspring.dominio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="Pelicula")
public class Pelicula{

    @Id
    @GeneratedValue
    private String id;

    @Column(name="titulo")
    private String name;

    @Column(name="ano")
    private int anyo;

    @Column(name="director")
    private String director;
}