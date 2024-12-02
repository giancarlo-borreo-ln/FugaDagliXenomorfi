package com.generation.fugadaglixenomorfi.model.repository;

import com.generation.fugadaglixenomorfi.model.NaveSpaziale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NaveSpazialeRepository extends JpaRepository<NaveSpaziale, Long> {

    Optional<NaveSpaziale> findByNome(String nome);

    boolean existsByTurnoCorrenteGreaterThan(int turno);
}