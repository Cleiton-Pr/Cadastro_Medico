package com.medicvoll.ApiMedic.Infra.Security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.medicvoll.ApiMedic.Dominio.Medico.Usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenServic {
    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario){
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secret);//senha secreta para gerar assin.token ()obs. a senha não pode ser texto aberto
            return JWT.create()
                    .withIssuer("API Med.Voll") //mettodo para identifcar qual API pertence
                    .withSubject(usuario.getUsuario())//quem é  o donmo do Token que esta relacionado nesse caso Ususario getUsuario
                    .withExpiresAt(dataExpira())//metodo utilizado para vencer o tokemseja: semanas e minutors. boas praticas de seguranca
                    .sign(algoritmo);
        } catch (JWTCreationException exception){
            throw new RuntimeException("erro ao gerartokem", exception);
        }


    }

    public String getsubject (String tokenJWT){

        try{
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)//Este método especifica o algoritmo que será usado para verificar a validade e autenticidade
                    .withIssuer("API Med.Voll")//identificar quem emitiu o token.
                    .build()//método finaliza a configuração da verificação do JWT e constrói o objeto
                    .verify(tokenJWT)//Este método realiza a verificação do token JWT especificado (tokenJWT). Ele verifica se a assinatura do token é válida, se o token não expirou e se outros critérios configurados foram atendidos.
                    .getSubject();
        }catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT invalido ou expirado");
        }
    }



    //metodo criado para validar a experiração do token (((.withExpiresAt(dataExpira()))))
    private Instant dataExpira() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
