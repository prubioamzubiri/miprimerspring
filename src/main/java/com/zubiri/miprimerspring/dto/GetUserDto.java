package com.zubiri.miprimerspring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetUserDto {
    
    private String username;
    private String email;
    private String roles;
}
