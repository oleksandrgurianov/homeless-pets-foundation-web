package fontys.sem3.hpfapi.business;

import fontys.sem3.hpfapi.dto.CreatePetRequestDTO;
import fontys.sem3.hpfapi.dto.CreatePetResponseDTO;

public interface CreatePetUseCase {
    CreatePetResponseDTO createPet(CreatePetRequestDTO request);
}
