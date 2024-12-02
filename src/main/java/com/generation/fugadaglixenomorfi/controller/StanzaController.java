package com.generation.fugadaglixenomorfi.controller;

import com.generation.fugadaglixenomorfi.model.Stanza;
import com.generation.fugadaglixenomorfi.model.TipoStanza;
import com.generation.fugadaglixenomorfi.service.StanzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stanze")
public class StanzaController {

    private final StanzaService stanzaService;

    @Autowired
    public StanzaController(StanzaService stanzaService) {
        this.stanzaService = stanzaService;
    }

    @GetMapping
    public ResponseEntity<List<Stanza>> getAllStanze() {
        List<Stanza> stanze = stanzaService.getAllStanze();
        return ResponseEntity.ok(stanze);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stanza> getStanzaById(@PathVariable Long id) {
        return stanzaService.getStanzaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/tipo")
    public ResponseEntity<List<Stanza>> getStanzeByTipo(@RequestParam TipoStanza tipo) {
        List<Stanza> stanze = stanzaService.getStanzeByTipo(tipo);
        return ResponseEntity.ok(stanze);
    }

    @GetMapping("/barricate")
    public ResponseEntity<List<Stanza>> getStanzeBarricate() {
        List<Stanza> stanzeBarricate = stanzaService.getStanzeBarricate();
        return ResponseEntity.ok(stanzeBarricate);
    }

    @GetMapping("/xenomorfi")
    public ResponseEntity<List<Stanza>> getStanzeConXenomorfi() {
        List<Stanza> stanzeConXenomorfi = stanzaService.getStanzeConXenomorfi();
        return ResponseEntity.ok(stanzeConXenomorfi);
    }

    @GetMapping("/umani")
    public ResponseEntity<List<Stanza>> getStanzeConUmani() {
        List<Stanza> stanzeConUmani = stanzaService.getStanzeConUmani();
        return ResponseEntity.ok(stanzeConUmani);
    }

    @PostMapping
    public ResponseEntity<Stanza> creaStanza(@RequestParam String nome,
                                             @RequestParam TipoStanza tipo,
                                             @RequestParam boolean barricata,
                                             @RequestParam Long naveId) {
        Stanza nuovaStanza = stanzaService.creaStanza(nome, tipo, barricata, naveId);
        return ResponseEntity.ok(nuovaStanza);
    }

    @PutMapping("/{id}/barricata")
    public ResponseEntity<Stanza> aggiornaStatoBarricata(@PathVariable Long id,
                                                         @RequestParam boolean stato) {
        try {
            Stanza stanzaAggiornata = stanzaService.aggiornaStatoBarricata(id, stato);
            return ResponseEntity.ok(stanzaAggiornata);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminaStanza(@PathVariable Long id) {
        try {
            stanzaService.eliminaStanza(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
