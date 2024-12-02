package com.generation.fugadaglixenomorfi.controller;

import com.generation.fugadaglixenomorfi.model.Umano;
import com.generation.fugadaglixenomorfi.service.UmanoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/umani")
public class UmanoController {

    private final UmanoService umanoService;

    @Autowired
    public UmanoController(UmanoService umanoService) {
        this.umanoService = umanoService;
    }

    @GetMapping
    public ResponseEntity<List<Umano>> getAllUmani() {
        List<Umano> umani = umanoService.getAllUmani();
        return ResponseEntity.ok(umani);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Umano> getUmanoById(@PathVariable Long id) {
        return umanoService.getUmanoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/stanza/{stanzaId}")
    public ResponseEntity<List<Umano>> getUmaniInStanza(@PathVariable Long stanzaId) {
        List<Umano> umani = umanoService.getUmaniInStanza(stanzaId);
        return ResponseEntity.ok(umani);
    }

    @PostMapping
    public ResponseEntity<Umano> creaUmano(@RequestParam String nome,
                                           @RequestParam int forza,
                                           @RequestParam int hp,
                                           @RequestParam Long stanzaId) {
        Umano nuovoUmano = umanoService.creaUmano(nome, forza, hp, stanzaId);
        return ResponseEntity.ok(nuovoUmano);
    }

    @PutMapping("/{id}/sposta")
    public ResponseEntity<Umano> spostaUmano(@PathVariable Long id,
                                             @RequestParam Long stanzaDestinazioneId) {
        try {
            Umano umanoSpostato = umanoService.spostaUmano(id, stanzaDestinazioneId);
            return ResponseEntity.ok(umanoSpostato);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/hp")
    public ResponseEntity<Umano> aggiornaHp(@PathVariable Long id,
                                            @RequestParam int hp) {
        try {
            Umano umanoAggiornato = umanoService.aggiornaHp(id, hp);
            return ResponseEntity.ok(umanoAggiornato);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminaUmano(@PathVariable Long id) {
        try {
            umanoService.eliminaUmano(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
