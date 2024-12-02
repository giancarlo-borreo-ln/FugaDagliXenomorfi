package com.generation.fugadaglixenomorfi.model.repository;

import com.generation.fugadaglixenomorfi.model.ModuloRiparazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModuloRiparazioneRepository extends JpaRepository<ModuloRiparazione, Long> {

    List<ModuloRiparazione> findByCompletatoFalse();

    List<ModuloRiparazione> findByStanzaId(Long stanzaId);

    List<ModuloRiparazione> findByProgressiLessThan(int progressi);

    int countByCompletatoFalse();

    long countByCompletatoTrue();
}
