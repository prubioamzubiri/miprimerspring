package com.zubiri.miprimerspring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInDto {

    private String username;
    private String password;
    private String password2;
    private String email;
    private String email2;
    
}
