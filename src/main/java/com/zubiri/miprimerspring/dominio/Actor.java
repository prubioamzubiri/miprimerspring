package com.zubiri.miprimerspring.dominio;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Actor extends Personal{

    private int sueldo_por_pelicula;
    private int numero_de_premios;

    public Actor(String name, int sueldo_por_pelicula, int numero_de_premios)
    {
        super(name);
        this.sueldo_por_pelicula = sueldo_por_pelicula;
        this.numero_de_premios = numero_de_premios;
    }
    
}
