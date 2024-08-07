package com.medicvoll.ApiMedic.Dominio.Medico;


import com.medicvoll.ApiMedic.Dominio.Medico.DTO.Atualizardados;
import com.medicvoll.ApiMedic.Dominio.Medico.DTO.MedicoDados;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name="medvoll")
@Table(name="medicos")
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String telefone;
    private String email;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private EnderecoMedico  enderecoMedico;
    private boolean ativo;


    public Medico(MedicoDados dados) {

        this.nome = dados.nome();
        this.telefone = dados.telefone();
        this.email = dados.email();
        this.especialidade = dados.especialidade();
        this.enderecoMedico = new EnderecoMedico(dados.endereco());
        this.crm = dados.crm();
        this.ativo = true;

    }

    public void atualizarDados(Atualizardados dados) {
        if(dados.nome()!=null){
            this.nome = dados.nome();

        }
        if(dados.email()!=null){
            this.email = dados.email();
        }

        if (dados.telefone()!=null){
            this.telefone = dados.telefone();
        }
        if (dados.endereco()!=null){
            this.enderecoMedico.atualizarEnderecoDados(dados.endereco());
        }



    }

    public void excluir() {
        this.ativo = false;
    }

//    public void incluir() {
//        this.ativo = true;
//    }
}
