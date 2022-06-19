package fontys.sem3.hpfapi.business.pet;

import fontys.sem3.hpfapi.dto.pet.PetDTO;

import java.util.Optional;

public interface GetPetUseCase {
    Optional<PetDTO> getPet(long petId);
}
