package com.generation.fugadaglixenomorfi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
