package fontys.sem3.hpfapi.business.pet;

import fontys.sem3.hpfapi.dto.pet.CreatePetRequestDTO;
import fontys.sem3.hpfapi.dto.pet.CreatePetResponseDTO;

public interface CreatePetUseCase {
    CreatePetResponseDTO createPet(CreatePetRequestDTO request);
}
