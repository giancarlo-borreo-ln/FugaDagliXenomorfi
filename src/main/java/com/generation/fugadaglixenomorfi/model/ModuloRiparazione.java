package com.generation.fugadaglixenomorfi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
