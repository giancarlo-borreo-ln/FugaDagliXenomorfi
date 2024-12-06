package com.generation.fugadaglixenomorfi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "xenomorfo")
public class Xenomorfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int hp;

    @Column(nullable = false)
    private int attacco;

    @Column(nullable = false)
    private boolean evoluto;

    @ManyToOne
    @JoinColumn(name = "stanza_id", nullable = false)
    private Stanza stanza;

    public Xenomorfo(){}

    public Xenomorfo(Long id, int hp, int attacco, boolean evoluto, Stanza stanza) {
        this.id = id;
        this.hp = hp;
        this.attacco = attacco;
        this.evoluto = evoluto;
        this.stanza = stanza;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttacco() {
        return attacco;
    }

    public void setAttacco(int attacco) {
        this.attacco = attacco;
    }

    public boolean isEvoluto() {
        return evoluto;
    }

    public void setEvoluto(boolean evoluto) {
        this.evoluto = evoluto;
    }

    public Stanza getStanza() {
        return stanza;
    }

    public void setStanza(Stanza stanza) {
        this.stanza = stanza;
    }
}
