package com.generation.fugadaglixenomorfi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
