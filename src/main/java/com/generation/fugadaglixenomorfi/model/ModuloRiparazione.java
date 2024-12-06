package com.generation.fugadaglixenomorfi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "modulo_riparazione")
public class ModuloRiparazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private int puntiRiparazioneTotali;

    @Column(nullable = false)
    private int progressi;

    @Column(nullable = false)
    private boolean completato;

    @ManyToOne
    @JoinColumn(name = "stanza_id", nullable = false)
    private Stanza stanza;

    public ModuloRiparazione(){}

    public ModuloRiparazione(Long id, String nome, int puntiRiparazioneTotali, int progressi, boolean completato, Stanza stanza) {
        this.id = id;
        this.nome = nome;
        this.puntiRiparazioneTotali = puntiRiparazioneTotali;
        this.progressi = progressi;
        this.completato = completato;
        this.stanza = stanza;
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

    public int getPuntiRiparazioneTotali() {
        return puntiRiparazioneTotali;
    }

    public void setPuntiRiparazioneTotali(int puntiRiparazioneTotali) {
        this.puntiRiparazioneTotali = puntiRiparazioneTotali;
    }

    public int getProgressi() {
        return progressi;
    }

    public void setProgressi(int progressi) {
        this.progressi = progressi;
    }

    public boolean isCompletato() {
        return completato;
    }

    public void setCompletato(boolean completato) {
        this.completato = completato;
    }

    public Stanza getStanza() {
        return stanza;
    }

    public void setStanza(Stanza stanza) {
        this.stanza = stanza;
    }
}
