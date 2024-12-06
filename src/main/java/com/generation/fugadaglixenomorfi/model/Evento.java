package com.generation.fugadaglixenomorfi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "evento")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descrizione;

    @Column(nullable = false)
    private int turno;

    @ManyToOne
    @JoinColumn(name = "stanza_id")
    private Stanza stanza;

    @ManyToOne
    @JoinColumn(name = "nave_id")
    private NaveSpaziale nave;

    public Evento(){}

    public Evento(String descrizione, int turno, NaveSpaziale nave){
        this.descrizione = descrizione;
        this.turno = turno;
        this.nave = nave;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public Stanza getStanza() {
        return stanza;
    }

    public void setStanza(Stanza stanza) {
        this.stanza = stanza;
    }

    public NaveSpaziale getNave() {
        return nave;
    }

    public void setNave(NaveSpaziale nave) {
        this.nave = nave;
    }
}
