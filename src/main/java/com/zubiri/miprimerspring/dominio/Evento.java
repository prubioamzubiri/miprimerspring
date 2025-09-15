package com.zubiri.miprimerspring.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Evento {
    
    @Id
    private String nombreDelEvento;

    private String lugar;

    private String pais;
}
