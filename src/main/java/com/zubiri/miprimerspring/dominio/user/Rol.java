package com.zubiri.miprimerspring.dominio.user;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public enum Rol{
    
    USER, ADMIN
}
