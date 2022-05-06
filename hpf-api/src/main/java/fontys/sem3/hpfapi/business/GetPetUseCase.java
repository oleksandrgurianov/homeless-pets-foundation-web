package fontys.sem3.hpfapi.business;

import fontys.sem3.hpfapi.dto.PetDTO;
import java.util.Optional;

public interface GetPetUseCase {
    Optional<PetDTO> getPet(long petId);
}
