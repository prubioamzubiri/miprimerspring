package com.zubiri.miprimerspring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PremioDTO {
    
    private String titulo;
    private int ano;
    private EventoDTO eventoDTO;

}
