package com.example.usuarios.repository;

import com.example.usuarios.entity.Usuario;
import com.example.usuarios.enums.EstadoUsuario;
import com.example.usuarios.enums.TipoUsuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    Page<Usuario> findByTipoUsuario(TipoUsuario tipoUsuario, Pageable pageable);
    List<Usuario> findByEstado(EstadoUsuario estado);
}
