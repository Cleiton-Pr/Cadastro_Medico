package com.medicvoll.ApiMedic.Infra.Security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static javax.management.Query.and;


@Configuration
@EnableWebSecurity
public class SecurityConfig  {
@Autowired
private SecurityFilter  securityFilter;
    /* A anotação @Bean serve para exportar uma classe para o Spring, fazendo com que ele consiga carregá-la e realizar a sua injeção de dependência em outras classes.*/
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
        return  http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .requestMatchers(HttpMethod.POST, "/login").permitAll()
                .anyRequest().authenticated()
                .and().addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }




}




/*



   (((public class SecurityConfig )))

Neste método, precisamos devolver um objeto securityFilterChain, contudo, não vamos instanciar esse objeto. Nos parênteses do método receberemos a
classe HttpSecurity, do Spring, e o chamaremos de http.


Precisamos fazer algumas configurações, no return digitaremos http.csrf().disable().
 Serve para desabilitarmos proteção contra-ataques do tipo CSRF (Cross-Site Request Forgery).

Estamos desabilitando esse tipo de ataque porque vamos trabalhar com autenticação via tokens.
 Nesse cenário, o próprio token é uma proteção contra esses tipos de ataques e ficaria repetitivo.

Digitaremos um ponto (".") e o método sessionManagement(), para mostrar o gerenciamento da sessão. Na sequência,
mais um ponto e o método sessionCreationPolicy(), qual a política de criação da sessão.
*/