package com.zubiri.miprimerspring.dominio;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public abstract class Premio {

    @EmbeddedId
    private PremioId premioId;


    @ManyToOne
    @JoinColumn(name="evento")
    private Evento evento;
    
}
