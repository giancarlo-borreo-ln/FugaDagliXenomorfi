package com.generation.fugadaglixenomorfi.service;

import com.generation.fugadaglixenomorfi.model.*;
import com.generation.fugadaglixenomorfi.model.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private NaveSpazialeRepository naveSpazialeRepository;

    @Autowired
    private StanzaRepository stanzaRepository;

    @Autowired
    private UmanoRepository umanoRepository;

    @Autowired
    private XenomorfoRepository xenomorfoRepository;

    @Autowired
    private ModuloRiparazioneRepository moduloRiparazioneRepository;

    @Autowired
    private EquipaggiamentoRepository equipaggiamentoRepository;

    public void inizializzaPartita(String nomeNave) {
        NaveSpaziale nave = new NaveSpaziale();
        nave.setNome(nomeNave);
        nave.setTurnoCorrente(0);
        naveSpazialeRepository.save(nave);

        Stanza comando = new Stanza(null, "Comando", TipoStanza.COMANDO, false, 0, 0, null, null, null, null, nave);
        Stanza deposito = new Stanza(null, "Deposito", TipoStanza.DEPOSITO, false, 0, 0, null, null, null, null, nave);
        stanzaRepository.save(comando);
        stanzaRepository.save(deposito);

        Umano umano = new Umano(null, "Riparatore", 20, 100, comando);
        umanoRepository.save(umano);

        Xenomorfo xenomorfo = new Xenomorfo(null, 400, 50, false, deposito);
        xenomorfoRepository.save(xenomorfo);

        ModuloRiparazione modulo = new ModuloRiparazione(null, "Motore Principale", 100, 0, false, comando);
        moduloRiparazioneRepository.save(modulo);
    }

    public void avanzareTurno() {
        List<NaveSpaziale> navi = naveSpazialeRepository.findAll();
        for (NaveSpaziale nave : navi) {
            nave.setTurnoCorrente(nave.getTurnoCorrente() + 1);
            naveSpazialeRepository.save(nave);
            generaEventoCasuale(nave);
        }
    }

    public boolean checkCondizioniVittoria() {
        return moduloRiparazioneRepository.countByCompletatoFalse() == 0;
    }

    public boolean checkCondizioniSconfitta() {
        return umanoRepository.count() == 0;
    }

    private void generaEventoCasuale(NaveSpaziale nave) {
        List<Stanza> stanze = stanzaRepository.findAll();
        if (stanze.isEmpty()) {
            return;
        }

        int eventoCasuale = (int) (Math.random() * 4);

        Stanza stanzaCasuale = stanze.get((int) (Math.random() * stanze.size()));

        switch (eventoCasuale) {
            case 0 -> {
                Xenomorfo nuovoXenomorfo = new Xenomorfo(null, 300, 50, false, stanzaCasuale);
                xenomorfoRepository.save(nuovoXenomorfo);
            }
            case 1 -> {
                TipoEquipaggiamento[] risorse = TipoEquipaggiamento.values();
                TipoEquipaggiamento risorsaCasuale = risorse[(int) (Math.random() * risorse.length)];
                Equipaggiamento nuovoEquipaggiamento = new Equipaggiamento(null, risorsaCasuale, 10, stanzaCasuale);
                equipaggiamentoRepository.save(nuovoEquipaggiamento);
            }
            case 2 -> {
                List<ModuloRiparazione> moduli = moduloRiparazioneRepository.findByStanzaId(stanzaCasuale.getId());
                if (!moduli.isEmpty()) {
                    ModuloRiparazione moduloCasuale = moduli.get(0);
                    moduloCasuale.setProgressi(Math.max(0, moduloCasuale.getProgressi() - 10));
                    moduloRiparazioneRepository.save(moduloCasuale);
                }
            }
            case 3 -> {
                List<Umano> umaniNellaStanza = umanoRepository.findByStanzaId(stanzaCasuale.getId());
                if (!umaniNellaStanza.isEmpty()) {
                    Umano umanoCasuale = umaniNellaStanza.get((int) (Math.random() * umaniNellaStanza.size()));
                    umanoCasuale.setHp(Math.max(0, umanoCasuale.getHp() - 20));
                    umanoRepository.save(umanoCasuale);
                }
            }
        }
    }
}
