package fontys.sem3.hpfapi.business;

import fontys.sem3.hpfapi.dto.PetDTO;
import fontys.sem3.hpfapi.dto.UserDTO;
import java.util.ArrayList;

public interface PetService {
    ArrayList<PetDTO> getSortedPetsBySearch(String search, boolean ascending, int customerId);
    PetDTO getPetById(int id);
    boolean createPet(PetDTO pet);
    boolean updatePet(PetDTO pet);
    boolean deletePet(PetDTO pet);
    boolean updateCustomerId(PetDTO pet, UserDTO user);
}
