package com.zubiri.miprimerspring.dominio;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
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
