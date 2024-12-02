package com.generation.fugadaglixenomorfi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameStatus {
    private int turnoCorrente;
    private int umaniVivi;
    private int moduliRiparati;
    private int xenomorfiPresenti;
}
