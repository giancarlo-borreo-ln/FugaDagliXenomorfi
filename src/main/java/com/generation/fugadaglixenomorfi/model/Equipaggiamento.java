package com.generation.fugadaglixenomorfi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "equipaggiamento")
public class Equipaggiamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoEquipaggiamento tipo;

    @Column(nullable = false)
    private int quantita;

    @ManyToOne
    @JoinColumn(name = "stanza_id", nullable = false)
    private Stanza stanza;

}
