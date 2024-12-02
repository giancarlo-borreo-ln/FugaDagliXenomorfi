package com.generation.fugadaglixenomorfi.model.repository;

import com.generation.fugadaglixenomorfi.model.Equipaggiamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipaggiamentoRepository extends JpaRepository<Equipaggiamento, Long> {

    List<Equipaggiamento> findByNome(String nome);

    List<Equipaggiamento> findByStanzaId(Long stanzaId);

    List<Equipaggiamento> findByQuantitaGreaterThan(int quantita);
}
