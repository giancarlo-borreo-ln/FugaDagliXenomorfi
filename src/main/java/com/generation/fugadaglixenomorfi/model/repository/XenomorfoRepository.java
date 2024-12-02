package com.generation.fugadaglixenomorfi.model.repository;

import com.generation.fugadaglixenomorfi.model.Xenomorfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface XenomorfoRepository extends JpaRepository<Xenomorfo, Long> {

    List<Xenomorfo> findByHpGreaterThan(int hp);

    List<Xenomorfo> findByAttaccoGreaterThan(int attacco);

    List<Xenomorfo> findByEvolutoTrue();

    List<Xenomorfo> findByStanzaId(Long stanzaId);

    int countByStanzaId(Long stanzaId);
}
