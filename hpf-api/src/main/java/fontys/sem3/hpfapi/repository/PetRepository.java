package fontys.sem3.hpfapi.repository;

import fontys.sem3.hpfapi.dto.PetDTO;
import fontys.sem3.hpfapi.dto.UserDTO;
import java.util.ArrayList;

public interface PetRepository {
    ArrayList<PetDTO> getSortedPetsBySearch(String search, boolean ascending, int customerId);
    PetDTO getPetById(int id);
    boolean petExists(PetDTO pet);
    void createPet(PetDTO pet);
    boolean updatePet(PetDTO pet);
    boolean deletePet(PetDTO pet);
    boolean updateCustomerId(PetDTO pet, UserDTO user);
}
