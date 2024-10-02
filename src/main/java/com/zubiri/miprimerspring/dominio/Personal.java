package com.zubiri.miprimerspring.dominio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="Personal")
public class Personal {

    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true)
    private String name;

    public Personal(String name)
    {
        this.name = name;
    }
    
}
