package grsu.java.pets.service;

import grsu.java.pets.dto.PetDto;
import grsu.java.pets.model.Pet;
import grsu.java.pets.repository.PetsRepository;
import grsu.java.pets.service.api.PetService;
import grsu.java.pets.util.Randomizer;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PetServiceImpl implements PetService {

    private final PetsRepository petsRepository;
    private final ModelMapper modelMapper;


    public PetServiceImpl(PetsRepository petsRepository, ModelMapper modelMapper) {
        this.petsRepository = petsRepository;
        this.modelMapper = modelMapper;
    }

    public PetDto findById(Long id) {
        Pet pet = petsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pets are not found!"));
        log.info("Find a pet!");
        return modelMapper.map(pet, PetDto.class);
    }

    public List<PetDto> findAll() {
        log.info("Find a pet!");
        return petsRepository.findAll().stream().map(pet -> modelMapper.map(pet, PetDto.class)).collect(Collectors.toList());
    }

    public PetDto savePet(PetDto petDto) {
        Pet pet = modelMapper.map(petDto, Pet.class);
        pet = petsRepository.save(pet);
        log.info("One more pet was added!");
        return modelMapper.map(pet, PetDto.class);
    }

    public void deleteById(Long id) {
        petsRepository.deleteById(id);
        log.info("One of pats died(((((");
    }

    @Transactional
    @Override
    public void startTournament() {
        log.info("Fight!!!!!");
        List<Pet> pets = petsRepository.findAll();

        int firstIndex = Randomizer.getInt(0, pets.size() - 1);
        Pet firstMachine = pets.get(firstIndex);
        pets.remove(firstIndex);

        int secondIndex = Randomizer.getInt(0, pets.size() - 1);
        Pet secondMachine = pets.get(secondIndex);

        log.info("Fight between: " + firstMachine.getName() + " (hit points: " + firstMachine.getHitPoints() +
                ") VS " + secondMachine.getName() + " (hit points: " + secondMachine.getHitPoints() + ")");

        boolean turn = true;

        while (firstMachine.getHitPoints() > 0 && secondMachine.getHitPoints() > 0) {
            int damage = Randomizer.getInt(0, 23);
            if (turn) {
                log.info(firstMachine.getName() + "  hit  " + damage + "!!!");
                secondMachine.setHitPoints(secondMachine.getHitPoints() - damage);
            } else {
                log.info(secondMachine.getName() + "  hit " + damage + " !!!");
                firstMachine.setHitPoints(firstMachine.getHitPoints() - damage);
            }
            turn = !turn;
        }

        if (turn) {
            log.info("Winner is  " + secondMachine.getName() + ". Survived with : " + secondMachine.getHitPoints()+ " hp");
        } else {
            log.info("Winner is " + firstMachine.getName() + ".Survived with : " + firstMachine.getHitPoints()+ "xp");
        }
    }
}
