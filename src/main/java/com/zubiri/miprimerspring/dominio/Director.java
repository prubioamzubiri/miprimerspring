package com.zubiri.miprimerspring.dominio;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Director extends Personal{

    @Nonnull
    private int sueldo_anual;

    @Builder
    public Director(int id, String name, int sueldo_anual)
    {
        super(id, name);
        this.sueldo_anual = sueldo_anual;
    }
    
    @Builder
    public Director(String name, int sueldo_anual)
    {
        super(name);
        this.sueldo_anual = sueldo_anual;
    }
}
