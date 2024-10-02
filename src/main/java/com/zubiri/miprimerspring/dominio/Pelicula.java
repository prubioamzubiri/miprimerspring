package com.zubiri.miprimerspring.dominio;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name="Pelicula")
public class Pelicula{

    @Id
    @GeneratedValue
    private int id;

    
    @Column(name="titulo", unique = true)
    private String name;

    @Column(name="ano")
    private int anyo;


    @ManyToOne
    @JoinColumn(name="id_director")
    private Director director;

    @ManyToMany
    @JoinTable(
        name = "pelicula_actor",
        joinColumns = @JoinColumn(name = "pelicula_id"),
        inverseJoinColumns = @JoinColumn(name="acotr_id")
    )
    private List<Actor> actores;

    @OneToMany(mappedBy = "pelicula",
                orphanRemoval = true,
                cascade = CascadeType.ALL,
                fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<PremioPelicula> premios;

    public Pelicula(String name,
                    int anyo, 
                    Director director, 
                    List<Actor> actores) {
        this.name = name;
        this.anyo = anyo;
        this.director = director;
        this.actores = actores;
        this.premios = new ArrayList<PremioPelicula>();
    }
}