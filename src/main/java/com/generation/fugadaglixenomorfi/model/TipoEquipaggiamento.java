package com.generation.fugadaglixenomorfi.model;

public enum TipoEquipaggiamento {
    CIBO("Cibo"),
    MUNIZIONI("Munizioni"),
    PARTI_RIPARAZIONE("Parti di Riparazione"),
    GAS_TOSSICO("Gas Tossico"),
    ARMA("Arma"),
    SCANSIONE("Strumenti di Scansione");

    private final String descrizione;

    TipoEquipaggiamento(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getDescrizione() {
        return descrizione;
    }
}
