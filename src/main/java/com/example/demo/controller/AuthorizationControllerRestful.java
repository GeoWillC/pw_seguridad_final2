package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.security.JwtUtils;
import com.example.demo.service.to.UsuarioTO;

@RestController
@RequestMapping(path = "/autorizaciones")
public class AuthorizationControllerRestful {

    //  /API/v1.0/Seguridad/autorizaciones/jwt

    @Autowired 
    private JwtUtils jwt;
        //Autorizacion 
        //Validar que el usuario y el password sean correctos
        @Autowired
        private AuthenticationManager authenticationManager;

        @GetMapping(path = "/jwt", produces = MediaType.TEXT_PLAIN_VALUE)
        public String obtenerToken(@RequestBody UsuarioTO usuario){
            this.autenticacion(usuario.getNombre(),usuario.getPassword());
            System.out.println("Check: "+usuario.getNombre()+usuario.getPassword() );
            return this.jwt.buildTokenJwt(usuario.getNombre());
          
        }

        private void autenticacion(String usuario,String password){
        UsernamePasswordAuthenticationToken usuarioToken=new UsernamePasswordAuthenticationToken(usuario,password);
        	System.out.println("Usuario token"+usuarioToken);
            Authentication autenticacion= this.authenticationManager.authenticate(usuarioToken);
            System.out.println("Autenteicacion"+autenticacion);
            SecurityContextHolder.getContext().setAuthentication(autenticacion);
        }
}