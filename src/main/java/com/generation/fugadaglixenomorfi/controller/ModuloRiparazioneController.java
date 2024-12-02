package com.generation.fugadaglixenomorfi.controller;

import com.generation.fugadaglixenomorfi.model.ModuloRiparazione;
import com.generation.fugadaglixenomorfi.service.ModuloRiparazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moduli")
public class ModuloRiparazioneController {

    private final ModuloRiparazioneService moduloRiparazioneService;

    @Autowired
    public ModuloRiparazioneController(ModuloRiparazioneService moduloRiparazioneService) {
        this.moduloRiparazioneService = moduloRiparazioneService;
    }

    @GetMapping
    public ResponseEntity<List<ModuloRiparazione>> getAllModuli() {
        List<ModuloRiparazione> moduli = moduloRiparazioneService.getAllModuli();
        return ResponseEntity.ok(moduli);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModuloRiparazione> getModuloById(@PathVariable Long id) {
        return moduloRiparazioneService.getModuloById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/stanza/{stanzaId}")
    public ResponseEntity<List<ModuloRiparazione>> getModuliInStanza(@PathVariable Long stanzaId) {
        List<ModuloRiparazione> moduli = moduloRiparazioneService.getModuliInStanza(stanzaId);
        return ResponseEntity.ok(moduli);
    }

    @GetMapping("/nonRiparati")
    public ResponseEntity<List<ModuloRiparazione>> getModuliNonRiparati() {
        List<ModuloRiparazione> moduliNonRiparati = moduloRiparazioneService.getModuliNonRiparati();
        return ResponseEntity.ok(moduliNonRiparati);
    }

    @PostMapping
    public ResponseEntity<ModuloRiparazione> creaModulo(@RequestParam String nome,
                                                        @RequestParam int puntiRiparazioneTotali,
                                                        @RequestParam Long stanzaId) {
        ModuloRiparazione nuovoModulo = moduloRiparazioneService.creaModulo(nome, puntiRiparazioneTotali, stanzaId);
        return ResponseEntity.ok(nuovoModulo);
    }

    @PutMapping("/{id}/progressi")
    public ResponseEntity<ModuloRiparazione> aggiornaProgressi(@PathVariable Long id,
                                                               @RequestParam int progressi) {
        try {
            ModuloRiparazione moduloAggiornato = moduloRiparazioneService.aggiornaProgressi(id, progressi);
            return ResponseEntity.ok(moduloAggiornato);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/danno")
    public ResponseEntity<ModuloRiparazione> riduciProgressi(@PathVariable Long id,
                                                             @RequestParam int danno) {
        try {
            ModuloRiparazione moduloDanneggiato = moduloRiparazioneService.riduciProgressi(id, danno);
            return ResponseEntity.ok(moduloDanneggiato);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminaModulo(@PathVariable Long id) {
        try {
            moduloRiparazioneService.eliminaModulo(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
