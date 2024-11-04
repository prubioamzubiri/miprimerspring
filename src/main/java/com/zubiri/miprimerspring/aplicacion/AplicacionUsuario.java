package com.zubiri.miprimerspring.aplicacion;

import com.zubiri.miprimerspring.dominio.usuario.Usuario;
import com.zubiri.miprimerspring.dto.userdtos.UserDtoConverter;
import com.zubiri.miprimerspring.dto.userdtos.UserGetDto;
import com.zubiri.miprimerspring.dto.userdtos.UserRegisterDto;
import com.zubiri.miprimerspring.persistencia.IPersistencia;

public class AplicacionUsuario extends Aplicacion<Usuario>{

    private UserDtoConverter userDtoConverter;

    public AplicacionUsuario(IPersistencia<Usuario> persistencia, UserDtoConverter userDtoConverter)
    {
        super(persistencia);
        this.userDtoConverter = userDtoConverter;
    }

    @Override
    public boolean guardar(Usuario usuario) {
        return false;
    }

    public UserGetDto guardar(UserRegisterDto usuario)
    {
        Usuario usuarioADevolver = null;

        try{
            if((usuario.getPassword().compareTo(usuario.getPasswordConfirm()) == 0)&&
            usuario.getEmail().compareTo(usuario.getEmailConfirm())==0)
            {

                usuarioADevolver = userDtoConverter.toUser(usuario);

                if(persistencia.guardar(usuarioADevolver))
                {
                    return userDtoConverter.toUserGetDto(usuarioADevolver);
                }
                else{

                    return null;

                }

            }
            else{
                return null;
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Usuario buscarPorNombre(String nombre) {
       
        return persistencia.query("username", nombre).getFirst();

    }

    public boolean verifyEmail(String email)
    {
        if(email.contains(" "))
        {
            return false;
        }
        if(email.contains("@"))
        {
            String cadena[] = email.split("@");
            if(cadena.length == 2)
            {
                if(cadena[1].contains("."))
                {
                    return true;
                }
            }
        }
        

        return false;


    }

    
}
