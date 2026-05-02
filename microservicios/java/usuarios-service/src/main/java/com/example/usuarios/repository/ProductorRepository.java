package com.example.usuarios.repository;

import com.example.usuarios.entity.Productor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductorRepository extends JpaRepository<Productor, Long> {
    Optional<Productor> findByCedula(String cedula);
    Optional<Productor> findByIdUsuario(Long idUsuario);
}
