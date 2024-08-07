package com.medicvoll.ApiMedic.Dominio.Medico.Usuario;


import com.medicvoll.ApiMedic.Infra.Security.TokenServic;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class UsuarioController {
    @Autowired
    private AuthenticationManager manager ;
    @Autowired
    private TokenServic tokenServic;



    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosLogin dados){
        var token = new UsernamePasswordAuthenticationToken(dados.usuario(), dados.password()) ;
        var authentication = manager.authenticate(token);
        var tokenValid = tokenServic.gerarToken((Usuario) authentication.getPrincipal());
        System.out.println(tokenValid);
            return ResponseEntity.ok(new TokenJwt(tokenValid));
    }

   /*
   Para usarmos o objeto, utilizamos o método .authenticate() chamando o objeto manager, isso dentro de efetuarLogin().
    No método authenticate(), precisamos passar um objeto do tipo username authentication token.

    Logo após, vamos guardar o retorno do objeto token em uma variável.

    Este método devolve o objeto que representa o usuário autenticado no sistema.

    ((( var token = new UsernamePasswordAuthenticationToken(dados.usuario(), dados.password()) ;)))
    UsernamePasswordAuthenticationToken - como se fosse um DTO do próprio Spring.

    Esse token é o login e a senha, e já está sendo representado no DTO DadosAutenticacao. No entanto, esse DTO não é o
    parâmetro esperado pelo Spring, ele espera uma classe dele próprio - e não uma classe do projeto.

    Portanto, na variável token criaremos a classe que representa o usuário e a senha. Após o new,
    vamos instanciar um objeto do tipo UsernamePasswordAuthenticationToken() passando como parâmetro o DTO,
    sendo dados.login(), e dados.senha().
    * */



}
