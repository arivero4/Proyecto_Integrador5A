package com.example.inspecciones.repository;

import com.example.inspecciones.entity.InspeccionFitosanitaria;
import com.example.inspecciones.enums.EstadoInspeccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InspeccionRepository extends JpaRepository<InspeccionFitosanitaria, Long> {
    List<InspeccionFitosanitaria> findByIdPredio(Long idPredio);
    List<InspeccionFitosanitaria> findByIdAsistente(Long idAsistente);
    List<InspeccionFitosanitaria> findByEstado(EstadoInspeccion estado);
}
