package com.generation.fugadaglixenomorfi.controller;

import com.generation.fugadaglixenomorfi.model.Evento;
import com.generation.fugadaglixenomorfi.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventi")
public class EventoController {

    private final EventoService eventoService;

    @Autowired
    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping
    public ResponseEntity<List<Evento>> getAllEventi() {
        List<Evento> eventi = eventoService.getAllEventi();
        return ResponseEntity.ok(eventi);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> getEventoById(@PathVariable Long id) {
        return eventoService.getEventoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/turno/{turno}")
    public ResponseEntity<List<Evento>> getEventiByTurno(@PathVariable int turno) {
        List<Evento> eventi = eventoService.getEventiByTurno(turno);
        return ResponseEntity.ok(eventi);
    }

    @GetMapping("/stanza/{stanzaId}")
    public ResponseEntity<List<Evento>> getEventiPerStanza(@PathVariable Long stanzaId) {
        List<Evento> eventi = eventoService.getEventiPerStanza(stanzaId);
        return ResponseEntity.ok(eventi);
    }

    @GetMapping("/nave/{naveId}")
    public ResponseEntity<List<Evento>> getEventiPerNave(@PathVariable Long naveId) {
        List<Evento> eventi = eventoService.getEventiPerNave(naveId);
        return ResponseEntity.ok(eventi);
    }

    @PostMapping
    public ResponseEntity<Evento> creaEvento(@RequestParam String descrizione,
                                             @RequestParam int turno,
                                             @RequestParam(required = false) Long naveId,
                                             @RequestParam(required = false) Long stanzaId) {
        Evento nuovoEvento = eventoService.creaEvento(descrizione, turno, naveId, stanzaId);
        return ResponseEntity.ok(nuovoEvento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> aggiornaDescrizioneEvento(@PathVariable Long id,
                                                            @RequestParam String nuovaDescrizione) {
        try {
            Evento eventoAggiornato = eventoService.aggiornaDescrizioneEvento(id, nuovaDescrizione);
            return ResponseEntity.ok(eventoAggiornato);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminaEvento(@PathVariable Long id) {
        try {
            eventoService.eliminaEvento(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
