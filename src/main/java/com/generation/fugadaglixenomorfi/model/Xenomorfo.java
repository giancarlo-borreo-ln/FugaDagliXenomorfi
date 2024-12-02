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
}
