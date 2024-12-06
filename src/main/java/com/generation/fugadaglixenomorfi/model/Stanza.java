package com.generation.fugadaglixenomorfi.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stanza")
public class Stanza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoStanza tipo;

    @Column(nullable = false)
    private boolean barricata;

    @Column(nullable = false)
    private int umaniPresenti;

    @Column(nullable = false)
    private int xenomorfiPresenti;

    @OneToMany(mappedBy = "stanza", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Equipaggiamento> equipaggiamenti = new ArrayList<>();

    @OneToMany(mappedBy = "stanza", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Umano> umani = new ArrayList<>();

    @OneToMany(mappedBy = "stanza", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Xenomorfo> xenomorfi = new ArrayList<>();

    @OneToMany(mappedBy = "stanza", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ModuloRiparazione> moduliRiparazione = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "nave_id", nullable = false)
    private NaveSpaziale nave;

    public Stanza(Long id, String nome, TipoStanza tipo, boolean barricata, int umaniPresenti, int xenomorfiPresenti, List<Equipaggiamento> equipaggiamenti, List<Umano> umani, List<Xenomorfo> xenomorfi, List<ModuloRiparazione> moduliRiparazione, NaveSpaziale nave) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.barricata = barricata;
        this.umaniPresenti = umaniPresenti;
        this.xenomorfiPresenti = xenomorfiPresenti;
        this.equipaggiamenti = equipaggiamenti;
        this.umani = umani;
        this.xenomorfi = xenomorfi;
        this.moduliRiparazione = moduliRiparazione;
        this.nave = nave;
    }

    public Stanza() {}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoStanza getTipo() {
        return tipo;
    }

    public void setTipo(TipoStanza tipo) {
        this.tipo = tipo;
    }

    public boolean isBarricata() {
        return barricata;
    }

    public void setBarricata(boolean barricata) {
        this.barricata = barricata;
    }

    public int getUmaniPresenti() {
        return umaniPresenti;
    }

    public void setUmaniPresenti(int umaniPresenti) {
        this.umaniPresenti = umaniPresenti;
    }

    public int getXenomorfiPresenti() {
        return xenomorfiPresenti;
    }

    public void setXenomorfiPresenti(int xenomorfiPresenti) {
        this.xenomorfiPresenti = xenomorfiPresenti;
    }

    public List<Equipaggiamento> getEquipaggiamenti() {
        return equipaggiamenti;
    }

    public void setEquipaggiamenti(List<Equipaggiamento> equipaggiamenti) {
        this.equipaggiamenti = equipaggiamenti;
    }

    public List<Umano> getUmani() {
        return umani;
    }

    public void setUmani(List<Umano> umani) {
        this.umani = umani;
    }

    public List<Xenomorfo> getXenomorfi() {
        return xenomorfi;
    }

    public void setXenomorfi(List<Xenomorfo> xenomorfi) {
        this.xenomorfi = xenomorfi;
    }

    public List<ModuloRiparazione> getModuliRiparazione() {
        return moduliRiparazione;
    }

    public void setModuliRiparazione(List<ModuloRiparazione> moduliRiparazione) {
        this.moduliRiparazione = moduliRiparazione;
    }

    public NaveSpaziale getNave() {
        return nave;
    }

    public void setNave(NaveSpaziale nave) {
        this.nave = nave;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
