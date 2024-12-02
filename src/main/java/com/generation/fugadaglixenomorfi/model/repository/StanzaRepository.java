package com.generation.fugadaglixenomorfi.model.repository;

import com.generation.fugadaglixenomorfi.model.Stanza;
import com.generation.fugadaglixenomorfi.model.TipoStanza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StanzaRepository extends JpaRepository<Stanza, Long> {

    List<Stanza> findByTipo(TipoStanza tipo);

    List<Stanza> findByBarricataTrue();

    List<Stanza> findByNaveId(Long naveId);
}