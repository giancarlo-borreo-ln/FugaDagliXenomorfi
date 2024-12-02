package com.generation.fugadaglixenomorfi.controller;

import com.generation.fugadaglixenomorfi.model.Xenomorfo;
import com.generation.fugadaglixenomorfi.service.XenomorfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/xenomorfi")
public class XenomorfoController {

    private final XenomorfoService xenomorfoService;

    @Autowired
    public XenomorfoController(XenomorfoService xenomorfoService) {
        this.xenomorfoService = xenomorfoService;
    }

    @GetMapping
    public ResponseEntity<List<Xenomorfo>> getAllXenomorfi() {
        List<Xenomorfo> xenomorfi = xenomorfoService.getAllXenomorfi();
        return ResponseEntity.ok(xenomorfi);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Xenomorfo> getXenomorfoById(@PathVariable Long id) {
        return xenomorfoService.getXenomorfoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/stanza/{stanzaId}")
    public ResponseEntity<List<Xenomorfo>> getXenomorfiInStanza(@PathVariable Long stanzaId) {
        List<Xenomorfo> xenomorfi = xenomorfoService.getXenomorfiInStanza(stanzaId);
        return ResponseEntity.ok(xenomorfi);
    }

    @PostMapping
    public ResponseEntity<Xenomorfo> creaXenomorfo(@RequestParam int hp,
                                                   @RequestParam int attacco,
                                                   @RequestParam boolean evoluto,
                                                   @RequestParam Long stanzaId) {
        Xenomorfo nuovoXenomorfo = xenomorfoService.creaXenomorfo(hp, attacco, evoluto, stanzaId);
        return ResponseEntity.ok(nuovoXenomorfo);
    }

    @PutMapping("/{id}/sposta")
    public ResponseEntity<Xenomorfo> spostaXenomorfo(@PathVariable Long id,
                                                     @RequestParam Long stanzaDestinazioneId) {
        try {
            Xenomorfo xenomorfoSpostato = xenomorfoService.spostaXenomorfo(id, stanzaDestinazioneId);
            return ResponseEntity.ok(xenomorfoSpostato);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/hp")
    public ResponseEntity<Xenomorfo> aggiornaHp(@PathVariable Long id,
                                                @RequestParam int hp) {
        try {
            Xenomorfo xenomorfoAggiornato = xenomorfoService.aggiornaHp(id, hp);
            return ResponseEntity.ok(xenomorfoAggiornato);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/evolvi")
    public ResponseEntity<Xenomorfo> evolviXenomorfo(@PathVariable Long id,
                                                     @RequestParam int bonusHp,
                                                     @RequestParam int bonusAttacco) {
        try {
            Xenomorfo xenomorfoEvoluto = xenomorfoService.evolviXenomorfo(id, bonusHp, bonusAttacco);
            return ResponseEntity.ok(xenomorfoEvoluto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminaXenomorfo(@PathVariable Long id) {
        try {
            xenomorfoService.eliminaXenomorfo(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
