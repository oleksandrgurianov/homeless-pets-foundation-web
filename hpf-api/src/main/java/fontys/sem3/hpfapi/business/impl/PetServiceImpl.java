package fontys.sem3.hpfapi.business.impl;

import fontys.sem3.hpfapi.business.PetService;
import fontys.sem3.hpfapi.dto.PetDTO;
import fontys.sem3.hpfapi.dto.UserDTO;
import fontys.sem3.hpfapi.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Primary
@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;

    @Override
    public ArrayList<PetDTO> getSortedPetsBySearch(String search, boolean ascending, int customerId) {
        return this.petRepository.getSortedPetsBySearch(search, ascending, customerId);
    }

    @Override
    public PetDTO getPetById(int id) {
        return this.petRepository.getPetById(id);
    }

    @Override
    public boolean createPet(PetDTO pet) {
        if (pet != null) {
            if (!pet.getPictures().isEmpty() && !pet.getType().isBlank() && !pet.getName().isBlank() && !pet.getBreed().isBlank()) {
                if (!this.petRepository.petExists(pet)) {
                    this.petRepository.createPet(pet);
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean updatePet(PetDTO pet) {
        if (pet != null) {
            if (!pet.getPictures().isEmpty() && !pet.getType().isBlank() && !pet.getName().isBlank() && !pet.getBreed().isBlank()) {
                if (!this.petRepository.petExists(pet)) {
                    return this.petRepository.updatePet(pet);
                }
            }
        }

        return false;
    }

    @Override
    public boolean deletePet(PetDTO pet) {
        if (pet != null) {
            return this.petRepository.deletePet(pet);
        }

        return false;
    }

    @Override
    public boolean updateCustomerId(PetDTO pet, UserDTO user) {
        if (pet != null && user != null) {
            return this.petRepository.updateCustomerId(pet, user);
        }

        return false;
    }
}
