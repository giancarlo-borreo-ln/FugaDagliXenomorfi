package com.generation.fugadaglixenomorfi.controller;

import com.generation.fugadaglixenomorfi.model.NaveSpaziale;
import com.generation.fugadaglixenomorfi.service.NaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/navi")
public class NaveSpazialeController {

    private final NaveService naveService;

    @Autowired
    public NaveSpazialeController(NaveService naveService) {
        this.naveService = naveService;
    }

    @GetMapping
    public ResponseEntity<List<NaveSpaziale>> getAllNavi() {
        List<NaveSpaziale> navi = naveService.getAllNavi();
        return ResponseEntity.ok(navi);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NaveSpaziale> getNaveById(@PathVariable Long id) {
        return naveService.getNaveById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<NaveSpaziale> creaNave(@RequestParam String nome) {
        NaveSpaziale nuovaNave = naveService.creaNave(nome);
        return ResponseEntity.ok(nuovaNave);
    }

    @PutMapping("/{id}/turno")
    public ResponseEntity<NaveSpaziale> aggiornaTurno(@PathVariable Long id) {
        try {
            NaveSpaziale naveAggiornata = naveService.aggiornaTurno(id);
            return ResponseEntity.ok(naveAggiornata);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminaNave(@PathVariable Long id) {
        try {
            naveService.eliminaNave(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
