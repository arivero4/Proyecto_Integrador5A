package com.example.predios.repository;

import com.example.predios.entity.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Long> {
    Optional<Municipio> findByNombre(String nombre);
    Optional<Municipio> findByCodigoDane(String codigoDane);
}
