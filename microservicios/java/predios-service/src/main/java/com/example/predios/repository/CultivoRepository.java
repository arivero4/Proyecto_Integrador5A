package com.example.predios.repository;

import com.example.predios.entity.Cultivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CultivoRepository extends JpaRepository<Cultivo, Long> {
    List<Cultivo> findByIdPredio(Long idPredio);
    List<Cultivo> findByTipoCultivo(String tipoCultivo);
    List<Cultivo> findByEstado(String estado);
}
