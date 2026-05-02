package com.example.predios.repository;

import com.example.predios.entity.Predio;
import com.example.predios.enums.EstadoPredio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PredioRepository extends JpaRepository<Predio, Long> {
    List<Predio> findByIdProductor(Long idProductor);
    List<Predio> findByIdMunicipio(Long idMunicipio);
    List<Predio> findByEstado(EstadoPredio estado);
}
