package fontys.sem3.hpfapi.repository;

import fontys.sem3.hpfapi.dto.PetDTO;
import java.util.ArrayList;

public interface PetRepository {
    ArrayList<PetDTO> getPetsBySearch(String search);
    ArrayList<PetDTO> getPetsSortedByName(boolean ascending);
    PetDTO getPetById(int id);
}
