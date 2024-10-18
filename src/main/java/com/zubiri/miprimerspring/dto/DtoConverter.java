package com.zubiri.miprimerspring.dto;

import com.zubiri.miprimerspring.dominio.Director;
import com.zubiri.miprimerspring.dominio.Evento;
import com.zubiri.miprimerspring.dominio.PremioPelicula;

public class DtoConverter {

    public static Evento toEvento(EventoDTO eventoDTO)
    {
        Evento respuesta = new Evento(eventoDTO.getNombreDelEvento(),
                                    eventoDTO.getLugar(),
                                    eventoDTO.getPais());

        return respuesta;
    }

    public static EventoDTO fromEvento(Evento evento){

        /*EventoDTO eventoDTO = new EventoDTO(evento.getNombreDelEvento(),
                                            evento.getLugar(),
                                            evento.getPais());
        
        return eventoDTO;*/

        EventoDTO toReurn = EventoDTO.builder()
                .nombreDelEvento(evento.getNombreDelEvento())
                .lugar(evento.getLugar())
                .build();

        return toReurn;
                                        
    }

    public static PremioPeliculaDTO fromPremio(PremioPelicula premio)
    {

        EventoDTO evento = fromEvento(premio.getEvento());
        
        PremioPeliculaDTO respuesta = new PremioPeliculaDTO(premio.getPremioId().getTitulo(),
                                                    premio.getPremioId().getAnyo(),
                                                    evento,
                                                    premio.getPelicula().getName());

        return respuesta;
    }

    public static Director fromDirector(DirectorDto directorDto)
    {
        Director respuesta = Director.builder()
                            .name(directorDto.getName())                            
                            .sueldo_anual(directorDto.getSueldo_anual())
                            .build();

        return respuesta;
    }
    
}
