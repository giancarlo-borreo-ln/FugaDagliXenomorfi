package com.generation.fugadaglixenomorfi.service;

import com.generation.fugadaglixenomorfi.model.NaveSpaziale;
import com.generation.fugadaglixenomorfi.model.repository.NaveSpazialeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NaveService {

    @Autowired
    private NaveSpazialeRepository naveSpazialeRepository;

    public List<NaveSpaziale> getAllNavi() {
        return naveSpazialeRepository.findAll();
    }

    public Optional<NaveSpaziale> getNaveById(Long id) {
        return naveSpazialeRepository.findById(id);
    }

    public NaveSpaziale creaNave(String nome) {
        NaveSpaziale nave = new NaveSpaziale();
        nave.setNome(nome);
        nave.setTurnoCorrente(0);
        return naveSpazialeRepository.save(nave);
    }

    public NaveSpaziale aggiornaTurno(Long id) {
        Optional<NaveSpaziale> naveOpt = naveSpazialeRepository.findById(id);
        if (naveOpt.isPresent()) {
            NaveSpaziale nave = naveOpt.get();
            nave.setTurnoCorrente(nave.getTurnoCorrente() + 1);
            return naveSpazialeRepository.save(nave);
        }
        throw new IllegalArgumentException("Nave non trovata con ID: " + id);
    }

    public boolean verificaVittoria(Long id) {
        Optional<NaveSpaziale> naveOpt = naveSpazialeRepository.findById(id);
        if (naveOpt.isPresent()) {
            NaveSpaziale nave = naveOpt.get();
            return nave.getTurnoCorrente() > 50; // Esempio: vittoria dopo 50 turni
        }
        return false;
    }

    public void eliminaNave(Long id) {
        if (naveSpazialeRepository.existsById(id)) {
            naveSpazialeRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Nave non trovata con ID: " + id);
        }
    }
}
