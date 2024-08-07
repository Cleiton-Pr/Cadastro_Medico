package com.medicvoll.ApiMedic.Infra.Security;


import com.medicvoll.ApiMedic.Dominio.Medico.Usuario.RepositoryUsuario;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
   private  TokenServic tokenServic;
    @Autowired
    private RepositoryUsuario repositoryUsuario;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var tokenJWT = recuperarToken(request);

        System.out.println("logado*****************************************");
        if(tokenJWT !=null){

            var subject = tokenServic.getsubject(tokenJWT);
            var usuario = repositoryUsuario.findByUsuario(subject);
            var authentic = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentic);
            System.out.println("logado*****************************************");
        }

        filterChain.doFilter(request,response);


    }

    private String recuperarToken(HttpServletRequest request) {
        var autorizar = request.getHeader("Authorization");
        if (autorizar !=null){
            return autorizar.replace("Bearer ", "");
        }

        return null;
    }
}

