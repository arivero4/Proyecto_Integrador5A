package com.example.inspecciones.repository;

import com.example.inspecciones.entity.Resultado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultadoRepository extends JpaRepository<Resultado, Long> {
    List<Resultado> findByIdInspeccion(Long idInspeccion);
    List<Resultado> findByEstado(String estado);
}
