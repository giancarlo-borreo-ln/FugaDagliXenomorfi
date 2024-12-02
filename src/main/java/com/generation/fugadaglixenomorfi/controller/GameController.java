package com.generation.fugadaglixenomorfi.controller;

import com.generation.fugadaglixenomorfi.dto.GameStatus;
import com.generation.fugadaglixenomorfi.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/start")
    public ResponseEntity<Void> startGame(@RequestParam String nomeNave) {
        gameService.inizializzaPartita(nomeNave);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/turn")
    public ResponseEntity<Void> advanceTurn() {
        gameService.avanzareTurno();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/victory")
    public ResponseEntity<Boolean> checkVictory() {
        boolean victory = gameService.checkCondizioniVittoria();
        return ResponseEntity.ok(victory);
    }

    @GetMapping("/defeat")
    public ResponseEntity<Boolean> checkDefeat() {
        boolean defeat = gameService.checkCondizioniSconfitta();
        return ResponseEntity.ok(defeat);
    }

    @GetMapping("/status")
    public ResponseEntity<GameStatus> getGameStatus() {
        GameStatus status = gameService.getGameStatus();
        return ResponseEntity.ok(status);
    }

    @GetMapping("/summary")
    public ResponseEntity<String> getGameSummary() {
        String summary = gameService.getGameSummary();
        return ResponseEntity.ok(summary);
    }
}
