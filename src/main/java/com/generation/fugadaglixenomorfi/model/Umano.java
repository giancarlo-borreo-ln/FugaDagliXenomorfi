package com.generation.fugadaglixenomorfi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "umano")
public class Umano {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private int forza;

    @Column(nullable = false)
    private int hp;

    @ManyToOne
    @JoinColumn(name = "stanza_id", nullable = false)
    private Stanza stanza;

    public Umano(Long id, String nome, int forza, int hp, Stanza stanza) {
        this.id = id;
        this.nome = nome;
        this.forza = forza;
        this.hp = hp;
        this.stanza = stanza;
    }

    public Umano() {}

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

    public int getForza() {
        return forza;
    }

    public void setForza(int forza) {
        this.forza = forza;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Stanza getStanza() {
        return stanza;
    }

    public void setStanza(Stanza stanza) {
        this.stanza = stanza;
    }
}
