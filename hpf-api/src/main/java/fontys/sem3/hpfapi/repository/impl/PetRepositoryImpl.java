package fontys.sem3.hpfapi.repository.impl;

import fontys.sem3.hpfapi.dto.PetDTO;
import fontys.sem3.hpfapi.dto.UserDTO;
import fontys.sem3.hpfapi.repository.PetRepository;
import fontys.sem3.hpfapi.repository.TemporaryDatabase;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Comparator;

@Primary
@Service
public class PetRepositoryImpl implements PetRepository {
    private final TemporaryDatabase temporaryDatabase = new TemporaryDatabase();

    @Override
    public ArrayList<PetDTO> getSortedPetsBySearch(String search, boolean ascending, int customerId) {
        ArrayList<PetDTO> pets = new ArrayList<>();

        for (PetDTO p : temporaryDatabase.petsList) {
            if (!search.isBlank()) {
                if (p.getType().contains(search) || p.getBreed().contains(search)) {
                    if (customerId == 0 || customerId == p.getCustomerId()) {
                        pets.add(p);
                    }
                }
            } else {
                pets.add(p);
            }
        }

        Comparator<PetDTO> compareByName =
                Comparator.comparing(PetDTO::getName);

        if (!pets.isEmpty()) {
            if (ascending) {
                pets.sort(compareByName);
            } else {
                pets.sort(compareByName.reversed());
            }
        }

        return pets;
    }

    @Override
    public PetDTO getPetById(int id) {
        for (PetDTO p : temporaryDatabase.petsList) {
            if (p.getId() == id && p.getCustomerId() == 0) {
                return p;
            }
        }

        return null;
    }

    @Override
    public boolean petExists(PetDTO pet) {
        for (PetDTO p : temporaryDatabase.petsList) {
            if (p.getType().equals(pet.getType()) && p.getName().equals(pet.getName()) && p.getBreed().equals(pet.getBreed())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void createPet(PetDTO pet) {
        this.temporaryDatabase.petsList.add(pet);
    }

    @Override
    public boolean updatePet(PetDTO pet) {
        for (PetDTO p : temporaryDatabase.petsList) {
            if (p.getId() == pet.getId()) {
                if (p.getCustomerId() == 0) {
                    p.setPictures(pet.getPictures());
                    p.setType(pet.getType());
                    p.setName(pet.getName());
                    p.setBreed(pet.getBreed());
                    p.setAgeCategory(pet.getAgeCategory());
                    p.setGender(pet.getGender());
                    p.setSize(pet.getSize());
                    p.setColor(pet.getColor());
                    p.setDescription(pet.getDescription());
                    p.setAdoptionFee(pet.getAdoptionFee());
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean deletePet(PetDTO pet) {
        for (PetDTO p : temporaryDatabase.petsList) {
            if (p.getId() == pet.getId()) {
                if (p.getCustomerId() == 0) {
                    this.temporaryDatabase.petsList.remove(p);
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean updateCustomerId(PetDTO pet, UserDTO user) {
        for (PetDTO p : temporaryDatabase.petsList) {
            if (p.getId() == pet.getId()) {
                if (p.getCustomerId() == 0) {
                    p.setCustomerId(user.getId());
                    return true;
                }
            }
        }

        return false;
    }
}
