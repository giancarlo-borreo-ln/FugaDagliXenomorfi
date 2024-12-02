package com.generation.fugadaglixenomorfi.service;

import com.generation.fugadaglixenomorfi.model.Stanza;
import com.generation.fugadaglixenomorfi.model.TipoStanza;
import com.generation.fugadaglixenomorfi.model.repository.StanzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StanzaService {

    @Autowired
    private StanzaRepository stanzaRepository;

    public List<Stanza> getAllStanze() {
        return stanzaRepository.findAll();
    }

    public Optional<Stanza> getStanzaById(Long id) {
        return stanzaRepository.findById(id);
    }

    public List<Stanza> getStanzeByTipo(TipoStanza tipo) {
        return stanzaRepository.findByTipo(tipo.name());
    }

    public List<Stanza> getStanzeBarricate() {
        return stanzaRepository.findByBarricataTrue();
    }

    public Stanza creaStanza(String nome, TipoStanza tipo, boolean barricata, Long naveId) {
        Stanza stanza = new Stanza();
        stanza.setNome(nome);
        stanza.setTipo(tipo);
        stanza.setBarricata(barricata);
        stanzaRepository.save(stanza);
        return stanza;
    }

    public Stanza aggiornaStatoBarricata(Long id, boolean stato) {
        Optional<Stanza> stanzaOpt = stanzaRepository.findById(id);
        if (stanzaOpt.isPresent()) {
            Stanza stanza = stanzaOpt.get();
            stanza.setBarricata(stato);
            return stanzaRepository.save(stanza);
        }
        throw new IllegalArgumentException("Stanza non trovata con ID: " + id);
    }

    public List<Stanza> getStanzeConXenomorfi() {
        return stanzaRepository.findByXenomorfiPresentiGreaterThan(0);
    }

    public List<Stanza> getStanzeConUmani() {
        return stanzaRepository.findByUmaniPresentiGreaterThan(0);
    }

    public void eliminaStanza(Long id) {
        if (stanzaRepository.existsById(id)) {
            stanzaRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Stanza non trovata con ID: " + id);
        }
    }
}
