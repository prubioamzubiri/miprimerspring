package com.zubiri.miprimerspring.dto.userdtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserGetDto {

    private String username;
    private String email;
    private String role;
    
}
