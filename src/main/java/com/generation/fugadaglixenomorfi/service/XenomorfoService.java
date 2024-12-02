package com.generation.fugadaglixenomorfi.service;

import com.generation.fugadaglixenomorfi.model.Stanza;
import com.generation.fugadaglixenomorfi.model.Xenomorfo;
import com.generation.fugadaglixenomorfi.model.repository.StanzaRepository;
import com.generation.fugadaglixenomorfi.model.repository.XenomorfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class XenomorfoService {

    @Autowired
    private XenomorfoRepository xenomorfoRepository;

    @Autowired
    private StanzaRepository stanzaRepository;

    public List<Xenomorfo> getAllXenomorfi() {
        return xenomorfoRepository.findAll();
    }

    public Optional<Xenomorfo> getXenomorfoById(Long id) {
        return xenomorfoRepository.findById(id);
    }

    public List<Xenomorfo> getXenomorfiInStanza(Long stanzaId) {
        return xenomorfoRepository.findByStanzaId(stanzaId);
    }

    public Xenomorfo creaXenomorfo(int hp, int attacco, boolean evoluto, Long stanzaId) {
        Optional<Stanza> stanzaOpt = stanzaRepository.findById(stanzaId);
        if (stanzaOpt.isPresent()) {
            Xenomorfo xenomorfo = new Xenomorfo();
            xenomorfo.setHp(hp);
            xenomorfo.setAttacco(attacco);
            xenomorfo.setEvoluto(evoluto);
            xenomorfo.setStanza(stanzaOpt.get());
            return xenomorfoRepository.save(xenomorfo);
        }
        throw new IllegalArgumentException("Stanza non trovata con ID: " + stanzaId);
    }

    public Xenomorfo spostaXenomorfo(Long xenomorfoId, Long stanzaDestinazioneId) {
        Optional<Xenomorfo> xenomorfoOpt = xenomorfoRepository.findById(xenomorfoId);
        Optional<Stanza> stanzaDestinazioneOpt = stanzaRepository.findById(stanzaDestinazioneId);

        if (xenomorfoOpt.isPresent() && stanzaDestinazioneOpt.isPresent()) {
            Xenomorfo xenomorfo = xenomorfoOpt.get();
            xenomorfo.setStanza(stanzaDestinazioneOpt.get());
            return xenomorfoRepository.save(xenomorfo);
        }
        throw new IllegalArgumentException("Xenomorfo o stanza non trovati");
    }

    public Xenomorfo aggiornaHp(Long xenomorfoId, int hp) {
        Optional<Xenomorfo> xenomorfoOpt = xenomorfoRepository.findById(xenomorfoId);
        if (xenomorfoOpt.isPresent()) {
            Xenomorfo xenomorfo = xenomorfoOpt.get();
            xenomorfo.setHp(Math.max(0, hp));
            return xenomorfoRepository.save(xenomorfo);
        }
        throw new IllegalArgumentException("Xenomorfo non trovato con ID: " + xenomorfoId);
    }

    public Xenomorfo evolviXenomorfo(Long xenomorfoId, int bonusHp, int bonusAttacco) {
        Optional<Xenomorfo> xenomorfoOpt = xenomorfoRepository.findById(xenomorfoId);
        if (xenomorfoOpt.isPresent()) {
            Xenomorfo xenomorfo = xenomorfoOpt.get();
            xenomorfo.setEvoluto(true);
            xenomorfo.setHp(xenomorfo.getHp() + bonusHp);
            xenomorfo.setAttacco(xenomorfo.getAttacco() + bonusAttacco);
            return xenomorfoRepository.save(xenomorfo);
        }
        throw new IllegalArgumentException("Xenomorfo non trovato con ID: " + xenomorfoId);
    }

    public void eliminaXenomorfo(Long id) {
        if (xenomorfoRepository.existsById(id)) {
            xenomorfoRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Xenomorfo non trovato con ID: " + id);
        }
    }
    public int getNumeroXenomorfiPresenti() {
        return (int) xenomorfoRepository.count();
    }

}
