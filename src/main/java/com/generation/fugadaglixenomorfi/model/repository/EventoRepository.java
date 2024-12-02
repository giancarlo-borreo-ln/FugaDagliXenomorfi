package com.generation.fugadaglixenomorfi.model.repository;

import com.generation.fugadaglixenomorfi.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {

    List<Evento> findByTurno(int turno);

    List<Evento> findByStanzaId(Long stanzaId);

    List<Evento> findByNaveId(Long naveId);
}
