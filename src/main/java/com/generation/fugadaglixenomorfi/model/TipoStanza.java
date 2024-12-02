package com.generation.fugadaglixenomorfi.model;

public enum TipoStanza {
    COMANDO("Comando"),
    DEPOSITO("Deposito"),
    LABORATORIO("Laboratorio"),
    MODULO("Modulo"),
    CORRIDOIO("Corridoio");

    private final String tipo;

    TipoStanza(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
