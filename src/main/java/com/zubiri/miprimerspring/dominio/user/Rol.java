package com.zubiri.miprimerspring.dominio.user;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Rol implements GrantedAuthority{
    
    @Id
    private String id;
    private String authority;

}
