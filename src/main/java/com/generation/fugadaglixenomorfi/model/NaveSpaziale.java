package com.generation.fugadaglixenomorfi.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "nave")
public class NaveSpaziale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "turno_corrente", nullable = false)
    private int turnoCorrente;

    @OneToMany(mappedBy = "nave", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Stanza> stanze = new ArrayList<>();

    public NaveSpaziale() {}

    public NaveSpaziale(Long id, String nome, int turnoCorrente, List<Stanza> stanze) {
        this.id = id;
        this.nome = nome;
        this.turnoCorrente = turnoCorrente;
        this.stanze = stanze;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTurnoCorrente() {
        return turnoCorrente;
    }

    public void setTurnoCorrente(int turnoCorrente) {
        this.turnoCorrente = turnoCorrente;
    }

    public List<Stanza> getStanze() {
        return stanze;
    }

    public void setStanze(List<Stanza> stanze) {
        this.stanze = stanze;
    }
}
