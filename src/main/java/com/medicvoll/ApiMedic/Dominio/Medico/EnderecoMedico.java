package com.medicvoll.ApiMedic.Dominio.Medico;



import com.medicvoll.ApiMedic.Dominio.Medico.DTO.Endereco;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Embeddable
public class EnderecoMedico {


    private String logradouro;
    private String bairro;
    private String cidade;
    private String complemento;
    private String cep;
    private String uf;
    private String numero;


    public EnderecoMedico(Endereco endereco) {
        this.logradouro = endereco.logradouro();
        this.bairro = endereco.bairro();
        this.cidade = endereco.cidade();
        this.complemento = endereco.complemento();
        this.cep = endereco.cep();
        this.uf = endereco.uf();
        this.numero = endereco.numero();
    }


    public void atualizarEnderecoDados(Endereco dados) {
        if (dados.logradouro()!=null){
            this.logradouro =dados.logradouro();
        }
        if(dados.bairro()!=null){
            this.bairro = dados.bairro();
        }

        if (dados.complemento()!=null){
                this.complemento = dados.complemento();}

        if(dados.cep()!=null){
            this.cep = dados.cep();
        }
        if(dados.uf()!=null){
            this.uf = dados.uf();
        }
        if(dados.numero()!=null){
            this.numero = dados.numero();
        }





    }
}
