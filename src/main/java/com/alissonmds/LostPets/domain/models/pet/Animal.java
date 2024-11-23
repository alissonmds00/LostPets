package com.alissonmds.LostPets.domain.models.pet;

public enum Animal {
    AVE,
    CACHORRO,
    GATO;

    public String animalToString() {
        return this.name();
    }
}
