package com.medicvoll.ApiMedic.Dominio.Medico.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface RepositoryUsuario extends JpaRepository<Usuario, Long> {
    UserDetails findByUsuario(String login);
}
