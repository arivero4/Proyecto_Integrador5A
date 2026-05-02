package com.example.usuarios.repository;

import com.example.usuarios.entity.AsistenteTecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AsistenteTecnicoRepository extends JpaRepository<AsistenteTecnico, Long> {
    Optional<AsistenteTecnico> findByCedula(String cedula);
    Optional<AsistenteTecnico> findByIdUsuario(Long idUsuario);
    Optional<AsistenteTecnico> findByNumeroLicencia(String numeroLicencia);
}
