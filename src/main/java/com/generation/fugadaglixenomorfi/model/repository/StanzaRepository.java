package com.generation.fugadaglixenomorfi.model.repository;

import com.generation.fugadaglixenomorfi.model.Stanza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StanzaRepository extends JpaRepository<Stanza, Long> {

    List<Stanza> findByXenomorfiPresentiGreaterThan(int numero);

    List<Stanza> findByUmaniPresentiGreaterThan(int numero);

    List<Stanza> findByTipo(String tipo);

    List<Stanza> findByBarricataTrue();
}
