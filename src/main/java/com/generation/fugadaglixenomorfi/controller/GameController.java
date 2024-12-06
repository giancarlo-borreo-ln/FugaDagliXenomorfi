package com.generation.fugadaglixenomorfi.controller;

import com.generation.fugadaglixenomorfi.dto.GameStatus;
import com.generation.fugadaglixenomorfi.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/start")
    public ResponseEntity<Void> startGame(@RequestParam String shipName) {
        gameService.initializeGame(shipName);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/turn")
    public ResponseEntity<Void> advanceTurn() {
        gameService.advanceTurn();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/status")
    public ResponseEntity<GameStatus> getGameStatus() {
        GameStatus gameStatus = gameService.getGameStatus();
        return ResponseEntity.ok(gameStatus);
    }

    @GetMapping("/summary")
    public ResponseEntity<String> getGameSummary() {
        String gameSummary = gameService.getGameSummary();
        return ResponseEntity.ok(gameSummary);
    }

    @PostMapping("/action/explore")
    public ResponseEntity<Map<String, String>> explore() {
        return createResponse(gameService.explore());
    }

    @PostMapping("/action/fight")
    public ResponseEntity<Map<String, String>> fight() {
        return createResponse(gameService.fight());
    }

    @PostMapping("/action/repair")
    public ResponseEntity<Map<String, String>> repair() {
        return createResponse(gameService.repair());
    }

    @PostMapping("/action/gather")
    public ResponseEntity<Map<String, String>> gatherResources() {
        return createResponse(gameService.gatherResources());
    }

    @PostMapping("/action/reinforce")
    public ResponseEntity<Map<String, String>> reinforce() {
        return createResponse(gameService.reinforce());
    }

    @PostMapping("/end")
    public ResponseEntity<Void> endGame() {
        gameService.endGame();
        return ResponseEntity.ok().build();
    }

    private ResponseEntity<Map<String, String>> createResponse(String message) {
        Map<String, String> response = Map.of("result", message);
        return ResponseEntity.ok(response);
    }
}
