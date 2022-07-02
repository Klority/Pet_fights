package grsu.java.pets.control;

import grsu.java.pets.dto.PetDto;
import grsu.java.pets.model.Pet;
import grsu.java.pets.service.api.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetsController {

    private final PetService petService;

    public PetsController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping
    public ResponseEntity<List<PetDto>> findAll(){
        return ResponseEntity.ok(petService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetDto> findPetById(@PathVariable Long id) {
        return ResponseEntity.ok(petService.findById(id));
    }

    @PostMapping
    public void createPetForm(@RequestBody PetDto petDto) {
//        return "pet-create";
        petService.savePet(petDto);
    }


    @DeleteMapping("/{id}")
    public void deletePet(@PathVariable("id") Long id){
        petService.deleteById(id);

    }



    @PutMapping
    public void petUpdate(PetDto petDto){
        petService.savePet(petDto);

    }

    @GetMapping("/tournament")
    public void startTournament() {
        petService.startTournament();
    }
}
