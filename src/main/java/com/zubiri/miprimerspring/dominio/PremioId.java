package com.zubiri.miprimerspring.dominio;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PremioId {
    
    private String titulo;
    private int anyo;
}
