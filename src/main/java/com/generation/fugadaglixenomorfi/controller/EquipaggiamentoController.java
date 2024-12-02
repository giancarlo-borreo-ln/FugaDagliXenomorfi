package com.generation.fugadaglixenomorfi.controller;

import com.generation.fugadaglixenomorfi.model.Equipaggiamento;
import com.generation.fugadaglixenomorfi.model.TipoEquipaggiamento;
import com.generation.fugadaglixenomorfi.service.EquipaggiamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipaggiamenti")
public class EquipaggiamentoController {

    private final EquipaggiamentoService equipaggiamentoService;

    @Autowired
    public EquipaggiamentoController(EquipaggiamentoService equipaggiamentoService) {
        this.equipaggiamentoService = equipaggiamentoService;
    }

    @GetMapping
    public ResponseEntity<List<Equipaggiamento>> getAllEquipaggiamenti() {
        List<Equipaggiamento> equipaggiamenti = equipaggiamentoService.getAllEquipaggiamenti();
        return ResponseEntity.ok(equipaggiamenti);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipaggiamento> getEquipaggiamentoById(@PathVariable Long id) {
        return equipaggiamentoService.getEquipaggiamentoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/stanza/{stanzaId}")
    public ResponseEntity<List<Equipaggiamento>> getEquipaggiamentiInStanza(@PathVariable Long stanzaId) {
        List<Equipaggiamento> equipaggiamenti = equipaggiamentoService.getEquipaggiamentiInStanza(stanzaId);
        return ResponseEntity.ok(equipaggiamenti);
    }

    @PostMapping
    public ResponseEntity<Equipaggiamento> creaEquipaggiamento(@RequestParam TipoEquipaggiamento tipo,
                                                               @RequestParam int quantita,
                                                               @RequestParam Long stanzaId) {
        Equipaggiamento nuovoEquipaggiamento = equipaggiamentoService.creaEquipaggiamento(tipo, quantita, stanzaId);
        return ResponseEntity.ok(nuovoEquipaggiamento);
    }

    @PutMapping("/{id}/quantita")
    public ResponseEntity<Equipaggiamento> aggiornaQuantita(@PathVariable Long id,
                                                            @RequestParam int quantita) {
        try {
            Equipaggiamento equipaggiamentoAggiornato = equipaggiamentoService.aggiornaQuantita(id, quantita);
            return ResponseEntity.ok(equipaggiamentoAggiornato);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/consuma")
    public ResponseEntity<Void> consumaEquipaggiamento(@PathVariable Long id,
                                                       @RequestParam int quantita) {
        try {
            equipaggiamentoService.consumaEquipaggiamento(id, quantita);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminaEquipaggiamento(@PathVariable Long id) {
        try {
            equipaggiamentoService.eliminaEquipaggiamento(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
