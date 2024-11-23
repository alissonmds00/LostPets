package com.alissonmds.LostPets.domain.models.pet;

public enum Situacao {
    ENCONTRADO,
    PERDIDO;

    public String situacaoToString() {
        return this.name();
    }


}
