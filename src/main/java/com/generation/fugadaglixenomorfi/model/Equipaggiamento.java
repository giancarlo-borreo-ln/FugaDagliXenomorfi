package com.generation.fugadaglixenomorfi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "equipaggiamento")
public class Equipaggiamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoEquipaggiamento tipo;

    @Column(nullable = false)
    private int quantita;

    @ManyToOne
    @JoinColumn(name = "stanza_id", nullable = false)
    private Stanza stanza;

    public Equipaggiamento() {}

    public Equipaggiamento(Long id, int quantita, String nome, TipoEquipaggiamento tipo, Stanza stanza) {
        this.id = id;
        this.quantita = quantita;
        this.nome = nome;
        this.tipo = tipo;
        this.stanza = stanza;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoEquipaggiamento getTipo() {
        return tipo;
    }

    public void setTipo(TipoEquipaggiamento tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public Stanza getStanza() {
        return stanza;
    }

    public void setStanza(Stanza stanza) {
        this.stanza = stanza;
    }
}
