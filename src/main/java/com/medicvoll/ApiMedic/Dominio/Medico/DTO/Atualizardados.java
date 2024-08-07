package com.medicvoll.ApiMedic.Dominio.Medico.DTO;

import jakarta.validation.constraints.NotNull;

public record Atualizardados(
        @NotNull
        Long id,
        String nome,
        String telefone,
        Endereco endereco,
        String email

) {
}
