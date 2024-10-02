package com.zubiri.miprimerspring.dominio;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class PremioPelicula extends Premio{

    @ManyToOne
    @JoinColumn(name="pelicula")
    @JsonBackReference
    private Pelicula pelicula;


    public PremioPelicula(PremioId premioId, Evento evento, Pelicula pelicula)
    {
        super(premioId, evento);
        this.pelicula = pelicula;
    }
    
}
