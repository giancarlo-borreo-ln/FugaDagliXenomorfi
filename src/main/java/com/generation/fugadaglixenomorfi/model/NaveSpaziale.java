package com.generation.fugadaglixenomorfi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.LinkedList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "nave")
public class NaveSpaziale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "turno_corrente", nullable = false)
    private int turnoCorrente;

    @OneToMany(mappedBy = "nave", cascade = CascadeType.ALL, orphanRemoval = true)
    private LinkedList<Stanza> stanze;

}
