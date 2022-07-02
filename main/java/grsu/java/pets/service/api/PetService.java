package grsu.java.pets.service.api;

import grsu.java.pets.dto.PetDto;

import java.util.List;

public interface PetService {

    PetDto findById(Long id);

    List<PetDto> findAll();

    PetDto savePet(PetDto petDto);

    void deleteById(Long id);

    void startTournament();
}
