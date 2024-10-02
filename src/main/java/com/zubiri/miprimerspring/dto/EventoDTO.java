package com.zubiri.miprimerspring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventoDTO {

    private String nombreDelEvento;
    private String lugar;
    private String pais;
    
}
