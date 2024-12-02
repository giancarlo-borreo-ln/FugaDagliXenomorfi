package com.generation.fugadaglixenomorfi.service;

import com.generation.fugadaglixenomorfi.model.Equipaggiamento;
import com.generation.fugadaglixenomorfi.model.Stanza;
import com.generation.fugadaglixenomorfi.model.TipoEquipaggiamento;
import com.generation.fugadaglixenomorfi.model.repository.EquipaggiamentoRepository;
import com.generation.fugadaglixenomorfi.model.repository.StanzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipaggiamentoService {

    @Autowired
    private EquipaggiamentoRepository equipaggiamentoRepository;

    @Autowired
    private StanzaRepository stanzaRepository;

    public List<Equipaggiamento> getAllEquipaggiamenti() {
        return equipaggiamentoRepository.findAll();
    }

    public Optional<Equipaggiamento> getEquipaggiamentoById(Long id) {
        return equipaggiamentoRepository.findById(id);
    }

    public List<Equipaggiamento> getEquipaggiamentiInStanza(Long stanzaId) {
        return equipaggiamentoRepository.findByStanzaId(stanzaId);
    }

    public Equipaggiamento creaEquipaggiamento(TipoEquipaggiamento tipo, int quantita, Long stanzaId) {
        Optional<Stanza> stanzaOpt = stanzaRepository.findById(stanzaId);
        if (stanzaOpt.isPresent()) {
            Equipaggiamento equipaggiamento = new Equipaggiamento();
            equipaggiamento.setTipo(tipo);
            equipaggiamento.setQuantita(quantita);
            equipaggiamento.setStanza(stanzaOpt.get());
            return equipaggiamentoRepository.save(equipaggiamento);
        }
        throw new IllegalArgumentException("Stanza non trovata con ID: " + stanzaId);
    }

    public Equipaggiamento aggiornaQuantita(Long equipaggiamentoId, int quantita) {
        Optional<Equipaggiamento> equipaggiamentoOpt = equipaggiamentoRepository.findById(equipaggiamentoId);
        if (equipaggiamentoOpt.isPresent()) {
            Equipaggiamento equipaggiamento = equipaggiamentoOpt.get();
            equipaggiamento.setQuantita(Math.max(0, quantita));
            return equipaggiamentoRepository.save(equipaggiamento);
        }
        throw new IllegalArgumentException("Equipaggiamento non trovato con ID: " + equipaggiamentoId);
    }

    public void consumaEquipaggiamento(Long equipaggiamentoId, int quantita) {
        Optional<Equipaggiamento> equipaggiamentoOpt = equipaggiamentoRepository.findById(equipaggiamentoId);
        if (equipaggiamentoOpt.isPresent()) {
            Equipaggiamento equipaggiamento = equipaggiamentoOpt.get();
            int nuovaQuantita = Math.max(0, equipaggiamento.getQuantita() - quantita);
            equipaggiamento.setQuantita(nuovaQuantita);
            equipaggiamentoRepository.save(equipaggiamento);
        } else {
            throw new IllegalArgumentException("Equipaggiamento non trovato con ID: " + equipaggiamentoId);
        }
    }

    public void eliminaEquipaggiamento(Long id) {
        if (equipaggiamentoRepository.existsById(id)) {
            equipaggiamentoRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Equipaggiamento non trovato con ID: " + id);
        }
    }
}
