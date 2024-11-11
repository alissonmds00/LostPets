package com.alissonmds.LostPets.repository;

import com.alissonmds.LostPets.domain.models.pet.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
