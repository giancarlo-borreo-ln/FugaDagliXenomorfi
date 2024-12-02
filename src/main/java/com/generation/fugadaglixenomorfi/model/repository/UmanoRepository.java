package com.generation.fugadaglixenomorfi.model.repository;

import com.generation.fugadaglixenomorfi.model.Umano;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UmanoRepository extends JpaRepository<Umano, Long> {

    List<Umano> findByNome(String nome);

    List<Umano> findByHpGreaterThan(int hp);

    List<Umano> findByForzaGreaterThan(int forza);

    List<Umano> findByStanzaId(Long stanzaId);

    int countByStanzaId(Long stanzaId);
}
