package com.zubiri.miprimerspring.dto.userdtos;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class LoginDto {

    private String username;
    private String password;
    
}
