package com.generation.fugadaglixenomorfi.service;

import com.generation.fugadaglixenomorfi.model.ModuloRiparazione;
import com.generation.fugadaglixenomorfi.model.Stanza;
import com.generation.fugadaglixenomorfi.model.repository.ModuloRiparazioneRepository;
import com.generation.fugadaglixenomorfi.model.repository.StanzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModuloRiparazioneService {

    @Autowired
    private ModuloRiparazioneRepository moduloRiparazioneRepository;

    @Autowired
    private StanzaRepository stanzaRepository;

    public List<ModuloRiparazione> getAllModuli() {
        return moduloRiparazioneRepository.findAll();
    }

    public Optional<ModuloRiparazione> getModuloById(Long id) {
        return moduloRiparazioneRepository.findById(id);
    }

    public List<ModuloRiparazione> getModuliInStanza(Long stanzaId) {
        return moduloRiparazioneRepository.findByStanzaId(stanzaId);
    }

    public List<ModuloRiparazione> getModuliNonRiparati() {
        return moduloRiparazioneRepository.findByCompletatoFalse();
    }

    public ModuloRiparazione creaModulo(String nome, int puntiRiparazioneTotali, Long stanzaId) {
        Optional<Stanza> stanzaOpt = stanzaRepository.findById(stanzaId);
        if (stanzaOpt.isPresent()) {
            ModuloRiparazione modulo = new ModuloRiparazione();
            modulo.setNome(nome);
            modulo.setPuntiRiparazioneTotali(puntiRiparazioneTotali);
            modulo.setProgressi(0);
            modulo.setCompletato(false);
            modulo.setStanza(stanzaOpt.get());
            return moduloRiparazioneRepository.save(modulo);
        }
        throw new IllegalArgumentException("Stanza non trovata con ID: " + stanzaId);
    }

    public ModuloRiparazione aggiornaProgressi(Long moduloId, int progressi) {
        Optional<ModuloRiparazione> moduloOpt = moduloRiparazioneRepository.findById(moduloId);
        if (moduloOpt.isPresent()) {
            ModuloRiparazione modulo = moduloOpt.get();
            int nuoviProgressi = Math.min(modulo.getPuntiRiparazioneTotali(), modulo.getProgressi() + progressi);
            modulo.setProgressi(nuoviProgressi);
            if (nuoviProgressi == modulo.getPuntiRiparazioneTotali()) {
                modulo.setCompletato(true);
            }
            return moduloRiparazioneRepository.save(modulo);
        }
        throw new IllegalArgumentException("Modulo non trovato con ID: " + moduloId);
    }

    public ModuloRiparazione riduciProgressi(Long moduloId, int danno) {
        Optional<ModuloRiparazione> moduloOpt = moduloRiparazioneRepository.findById(moduloId);
        if (moduloOpt.isPresent()) {
            ModuloRiparazione modulo = moduloOpt.get();
            int nuoviProgressi = Math.max(0, modulo.getProgressi() - danno);
            modulo.setProgressi(nuoviProgressi);
            if (nuoviProgressi < modulo.getPuntiRiparazioneTotali()) {
                modulo.setCompletato(false);
            }
            return moduloRiparazioneRepository.save(modulo);
        }
        throw new IllegalArgumentException("Modulo non trovato con ID: " + moduloId);
    }

    public void eliminaModulo(Long id) {
        if (moduloRiparazioneRepository.existsById(id)) {
            moduloRiparazioneRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Modulo non trovato con ID: " + id);
        }
    }
    public int getNumeroModuliRiparati() {
        return Math.toIntExact(moduloRiparazioneRepository.countByCompletatoTrue());
    }

}
