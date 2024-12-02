package com.generation.fugadaglixenomorfi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
