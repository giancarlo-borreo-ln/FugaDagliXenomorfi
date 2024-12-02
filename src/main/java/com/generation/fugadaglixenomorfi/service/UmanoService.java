package com.generation.fugadaglixenomorfi.service;

import com.generation.fugadaglixenomorfi.model.Stanza;
import com.generation.fugadaglixenomorfi.model.Umano;
import com.generation.fugadaglixenomorfi.model.repository.StanzaRepository;
import com.generation.fugadaglixenomorfi.model.repository.UmanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UmanoService {

    @Autowired
    private UmanoRepository umanoRepository;

    @Autowired
    private StanzaRepository stanzaRepository;

    public List<Umano> getAllUmani() {
        return umanoRepository.findAll();
    }

    public Optional<Umano> getUmanoById(Long id) {
        return umanoRepository.findById(id);
    }

    public List<Umano> getUmaniInStanza(Long stanzaId) {
        return umanoRepository.findByStanzaId(stanzaId);
    }

    public Umano creaUmano(String nome, int forza, int hp, Long stanzaId) {
        Optional<Stanza> stanzaOpt = stanzaRepository.findById(stanzaId);
        if (stanzaOpt.isPresent()) {
            Umano umano = new Umano();
            umano.setNome(nome);
            umano.setForza(forza);
            umano.setHp(hp);
            umano.setStanza(stanzaOpt.get());
            return umanoRepository.save(umano);
        }
        throw new IllegalArgumentException("Stanza non trovata con ID: " + stanzaId);
    }

    public Umano spostaUmano(Long umanoId, Long stanzaDestinazioneId) {
        Optional<Umano> umanoOpt = umanoRepository.findById(umanoId);
        Optional<Stanza> stanzaDestinazioneOpt = stanzaRepository.findById(stanzaDestinazioneId);

        if (umanoOpt.isPresent() && stanzaDestinazioneOpt.isPresent()) {
            Umano umano = umanoOpt.get();
            umano.setStanza(stanzaDestinazioneOpt.get());
            return umanoRepository.save(umano);
        }
        throw new IllegalArgumentException("Umano o stanza non trovati");
    }

    public Umano aggiornaHp(Long umanoId, int hp) {
        Optional<Umano> umanoOpt = umanoRepository.findById(umanoId);
        if (umanoOpt.isPresent()) {
            Umano umano = umanoOpt.get();
            umano.setHp(Math.max(0, hp));
            return umanoRepository.save(umano);
        }
        throw new IllegalArgumentException("Umano non trovato con ID: " + umanoId);
    }

    public void eliminaUmano(Long id) {
        if (umanoRepository.existsById(id)) {
            umanoRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Umano non trovato con ID: " + id);
        }
    }
    public int getNumeroUmaniVivi() {
        return (int) umanoRepository.count();
    }
}
