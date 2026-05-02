package com.example.inspecciones.repository;

import com.example.inspecciones.entity.Plaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlagaRepository extends JpaRepository<Plaga, Long> {
    List<Plaga> findByIdInspeccion(Long idInspeccion);
    List<Plaga> findBySeveridad(String severidad);
    List<Plaga> findByTipoPlaga(String tipoPlaga);
}
