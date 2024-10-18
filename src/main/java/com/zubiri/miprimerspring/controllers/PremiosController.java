package com.zubiri.miprimerspring.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zubiri.miprimerspring.aplicacion.IAplicacionPeliculas;
import com.zubiri.miprimerspring.dominio.Evento;
import com.zubiri.miprimerspring.dominio.Pelicula;
import com.zubiri.miprimerspring.dominio.Premio;
import com.zubiri.miprimerspring.dominio.PremioId;
import com.zubiri.miprimerspring.dominio.PremioPelicula;
import com.zubiri.miprimerspring.dto.DtoConverter;
import com.zubiri.miprimerspring.dto.PremioPeliculaDTO;
import com.zubiri.miprimerspring.persistencia.IPersistenciaPremios;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@AllArgsConstructor
@RestController
@RequestMapping("/premio")
public class PremiosController {

    private IPersistenciaPremios persistenciaPremios;
    private IAplicacionPeliculas aplicacionPeliculas;

    @GetMapping("/insertData")
    public String insertData() {

        String respuesta = "Fallo";

        Evento evento = new Evento("Oscars", "Hollywood", "EEUU");

        persistenciaPremios.addEvento(evento);

        Pelicula padrino = aplicacionPeliculas.getPelicula(302);

        PremioId premioId1 = new PremioId("Mejor Peli", 1972),
                 premioId2 = new PremioId("Mejor Drama", 1972);

        PremioPelicula premio1 = new PremioPelicula(premioId1, evento, padrino),
                       premio2 = new PremioPelicula(premioId2, evento, padrino);

        boolean ok = persistenciaPremios.addPremio(premio1);
        persistenciaPremios.addPremio(premio2);

        if(ok)
        {
            respuesta = "TODO OK";
        }
        return respuesta;
    }

    @GetMapping("/{tituloPremio}/{anyo}")
    public PremioPeliculaDTO getPremio(@PathVariable String tituloPremio, @PathVariable int anyo) {
        
        PremioPeliculaDTO respuesta = null;

        PremioId premioId = new PremioId(tituloPremio, anyo);

        Premio p = persistenciaPremios.getPremio(premioId);

        try{
            PremioPelicula premioPelicula = (PremioPelicula) p;
            respuesta = DtoConverter.fromPremio(premioPelicula);

        }
        catch(Exception e)
        {

        }

        return respuesta;

    }
    
    
    
}
