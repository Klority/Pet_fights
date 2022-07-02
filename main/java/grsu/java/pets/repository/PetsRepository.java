package grsu.java.pets.repository;

import grsu.java.pets.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetsRepository extends JpaRepository<Pet, Long> {
}
