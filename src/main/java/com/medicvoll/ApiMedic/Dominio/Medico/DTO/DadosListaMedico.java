package com.medicvoll.ApiMedic.Dominio.Medico.DTO;

import com.medicvoll.ApiMedic.Dominio.Medico.EnderecoMedico;
import com.medicvoll.ApiMedic.Dominio.Medico.Especialidade;
import com.medicvoll.ApiMedic.Dominio.Medico.Medico;

public record DadosListaMedico(
        Long id,
        String nome,
        String telefone,
        String email,
        String crm,
        Especialidade especialidade,
        EnderecoMedico Endereco
) {
    public DadosListaMedico (Medico medico){
        this(medico.getId(), medico.getNome(), medico.getTelefone(),
                medico.getEmail(), medico.getCrm(), medico.getEspecialidade(), medico.getEnderecoMedico());
    }
}
