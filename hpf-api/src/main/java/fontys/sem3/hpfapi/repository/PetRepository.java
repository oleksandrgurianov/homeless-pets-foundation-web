package fontys.sem3.hpfapi.repository;

import fontys.sem3.hpfapi.dto.PetDTO;
import java.util.List;

public interface PetRepository {
    List<PetDTO> getPets();
}
