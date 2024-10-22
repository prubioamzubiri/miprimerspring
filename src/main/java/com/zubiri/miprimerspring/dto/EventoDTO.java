package com.zubiri.miprimerspring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventoDTO {

    private String nombreDelEvento;
    private String lugar;
    private String pais;
    
}
