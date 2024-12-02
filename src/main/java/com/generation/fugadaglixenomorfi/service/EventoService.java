package com.generation.fugadaglixenomorfi.service;

import com.generation.fugadaglixenomorfi.model.Evento;
import com.generation.fugadaglixenomorfi.model.NaveSpaziale;
import com.generation.fugadaglixenomorfi.model.Stanza;
import com.generation.fugadaglixenomorfi.model.repository.EventoRepository;
import com.generation.fugadaglixenomorfi.model.repository.NaveSpazialeRepository;
import com.generation.fugadaglixenomorfi.model.repository.StanzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private NaveSpazialeRepository naveSpazialeRepository;

    @Autowired
    private StanzaRepository stanzaRepository;

    public List<Evento> getAllEventi() {
        return eventoRepository.findAll();
    }

    public Optional<Evento> getEventoById(Long id) {
        return eventoRepository.findById(id);
    }

    public List<Evento> getEventiByTurno(int turno) {
        return eventoRepository.findByTurno(turno);
    }

    public List<Evento> getEventiPerNave(Long naveId) {
        return eventoRepository.findByNaveId(naveId);
    }

    public List<Evento> getEventiPerStanza(Long stanzaId) {
        return eventoRepository.findByStanzaId(stanzaId);
    }

    public Evento creaEvento(String descrizione, int turno, Long naveId, Long stanzaId) {
        Optional<NaveSpaziale> naveOpt = naveSpazialeRepository.findById(naveId);
        Optional<Stanza> stanzaOpt = stanzaRepository.findById(stanzaId);

        Evento evento = new Evento();
        evento.setDescrizione(descrizione);
        evento.setTurno(turno);

        if (naveOpt.isPresent()) {
            evento.setNave(naveOpt.get());
        }

        if (stanzaOpt.isPresent()) {
            evento.setStanza(stanzaOpt.get());
        }

        return eventoRepository.save(evento);
    }

    public Evento aggiornaDescrizioneEvento(Long eventoId, String nuovaDescrizione) {
        Optional<Evento> eventoOpt = eventoRepository.findById(eventoId);
        if (eventoOpt.isPresent()) {
            Evento evento = eventoOpt.get();
            evento.setDescrizione(nuovaDescrizione);
            return eventoRepository.save(evento);
        }
        throw new IllegalArgumentException("Evento non trovato con ID: " + eventoId);
    }

    public void eliminaEvento(Long id) {
        if (eventoRepository.existsById(id)) {
            eventoRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Evento non trovato con ID: " + id);
        }
    }
}
