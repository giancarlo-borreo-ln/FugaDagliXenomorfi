package com.generation.fugadaglixenomorfi.service;

import com.generation.fugadaglixenomorfi.dto.GameStatus;
import com.generation.fugadaglixenomorfi.model.*;
import com.generation.fugadaglixenomorfi.model.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class GameService {

    private final NaveSpazialeRepository naveRepository;
    private final StanzaRepository stanzaRepository;
    private final UmanoRepository umanoRepository;
    private final XenomorfoRepository xenomorfoRepository;
    private final ModuloRiparazioneRepository moduloRepository;
    private final EquipaggiamentoRepository equipaggiamentoRepository;

    @Autowired
    public GameService(NaveSpazialeRepository naveRepository, StanzaRepository stanzaRepository, UmanoRepository umanoRepository,
                       XenomorfoRepository xenomorfoRepository, ModuloRiparazioneRepository moduloRepository,
                       EquipaggiamentoRepository equipaggiamentoRepository) {
        this.naveRepository = naveRepository;
        this.stanzaRepository = stanzaRepository;
        this.umanoRepository = umanoRepository;
        this.xenomorfoRepository = xenomorfoRepository;
        this.moduloRepository = moduloRepository;
        this.equipaggiamentoRepository = equipaggiamentoRepository;
    }

    public void initializeGame(String shipName) {
        NaveSpaziale nave = new NaveSpaziale();
        nave.setNome(shipName);
        nave.setTurnoCorrente(0);
        naveRepository.save(nave);

        Stanza mainRoom = new Stanza(null, "Comando", TipoStanza.COMANDO, false, 0, 0, null, null, null, null, nave);
        Stanza depot = new Stanza(null, "Deposito", TipoStanza.DEPOSITO, false, 0, 0, null, null, null, null, nave);
        stanzaRepository.saveAll(List.of(mainRoom, depot));

        Umano repairman = new Umano(null, "Riparatore", 20, 100, mainRoom);
        umanoRepository.save(repairman);

        Xenomorfo alien = new Xenomorfo(null, 300, 50, false, depot);
        xenomorfoRepository.save(alien);

        ModuloRiparazione engine = new ModuloRiparazione(null, "Motore Principale", 100, 0, false, mainRoom);
        moduloRepository.save(engine);
    }

    public void advanceTurn() {
        List<NaveSpaziale> navi = naveRepository.findAll();
        for (NaveSpaziale nave : navi) {
            nave.setTurnoCorrente(nave.getTurnoCorrente() + 1);
            naveRepository.save(nave);
            triggerRandomEvent(nave);
        }
    }

    public GameStatus getGameStatus() {
        int turnoCorrente = naveRepository.findAll().stream().mapToInt(NaveSpaziale::getTurnoCorrente).max().orElse(0);
        int umaniVivi = (int) umanoRepository.findAll().stream().filter(umano -> umano.getHp() > 0).count();
        int moduliRiparati = (int) moduloRepository.findAll().stream().filter(ModuloRiparazione::isCompletato).count();
        int xenomorfiPresenti = (int) xenomorfoRepository.count();

        return new GameStatus(turnoCorrente, umaniVivi, moduliRiparati, xenomorfiPresenti);
    }

    public String getGameSummary() {
        GameStatus status = getGameStatus();
        return String.format(
                "Turno Corrente: %d\nUmani Vivi: %d\nModuli Riparati: %d\nXenomorfi Presenti: %d",
                status.getTurnoCorrente(), status.getUmaniVivi(), status.getModuliRiparati(), status.getXenomorfiPresenti()
        );
    }

    public String explore() {
        List<Stanza> stanze = stanzaRepository.findAll();
        if (stanze.isEmpty()) {
            return "Non ci sono stanze da esplorare.";
        }
        Stanza stanza = stanze.get(new Random().nextInt(stanze.size()));
        return "Esplori la stanza: " + stanza.getNome();
    }

    public String fight() {
        List<Xenomorfo> xenomorfi = xenomorfoRepository.findAll();
        if (xenomorfi.isEmpty()) {
            return "Non ci sono Xenomorfi da combattere.";
        }
        Xenomorfo xenomorfo = xenomorfi.get(new Random().nextInt(xenomorfi.size()));
        xenomorfo.setHp(xenomorfo.getHp() - 50);
        if (xenomorfo.getHp() <= 0) {
            xenomorfoRepository.delete(xenomorfo);
            return "Hai sconfitto uno Xenomorfo!";
        }
        xenomorfoRepository.save(xenomorfo);
        return "Hai ferito uno Xenomorfo, ma è ancora vivo!";
    }

    public String repair() {
        List<ModuloRiparazione> moduli = moduloRepository.findAll();
        if (moduli.isEmpty()) {
            return "Non ci sono moduli da riparare.";
        }
        ModuloRiparazione modulo = moduli.get(new Random().nextInt(moduli.size()));
        modulo.setProgressi(modulo.getProgressi() + 20);
        if (modulo.getProgressi() >= modulo.getPuntiRiparazioneTotali()) {
            modulo.setCompletato(true);
        }
        moduloRepository.save(modulo);
        return "Hai riparato un modulo: " + modulo.getNome();
    }

    public String gatherResources() {
        List<Equipaggiamento> equipaggiamenti = equipaggiamentoRepository.findAll();
        if (equipaggiamenti.isEmpty()) {
            return "Non ci sono risorse da raccogliere.";
        }
        Equipaggiamento equipaggiamento = equipaggiamenti.get(new Random().nextInt(equipaggiamenti.size()));
        equipaggiamentoRepository.delete(equipaggiamento);
        return "Hai raccolto: " + equipaggiamento.getNome();
    }

    public String reinforce() {
        List<Umano> umani = umanoRepository.findAll();
        if (umani.isEmpty()) {
            return "Non ci sono umani da rinforzare.";
        }
        Umano umano = umani.get(new Random().nextInt(umani.size()));
        umano.setHp(Math.min(100, umano.getHp() + 20));
        umanoRepository.save(umano);
        return "Hai rinforzato un umano: " + umano.getNome();
    }

    public void endGame() {
        naveRepository.deleteAll();
        stanzaRepository.deleteAll();
        umanoRepository.deleteAll();
        xenomorfoRepository.deleteAll();
        moduloRepository.deleteAll();
        equipaggiamentoRepository.deleteAll();
    }

    private void triggerRandomEvent(NaveSpaziale nave) {
        List<Stanza> stanze = stanzaRepository.findByNaveId(nave.getId());
        if (stanze.isEmpty()) return;

        Stanza stanza = stanze.get(new Random().nextInt(stanze.size()));
        switch (new Random().nextInt(3)) {
            case 0 -> xenomorfoRepository.save(new Xenomorfo(null, 300, 50, false, stanza));
            case 1 -> umanoRepository.findByStanzaId(stanza.getId()).forEach(umano -> {
                umano.setHp(Math.max(0, umano.getHp() - 10));
                umanoRepository.save(umano);
            });
            case 2 -> moduloRepository.findByStanzaId(stanza.getId()).forEach(modulo -> {
                modulo.setProgressi(Math.max(0, modulo.getProgressi() - 10));
                moduloRepository.save(modulo);
            });
        }
    }
}
