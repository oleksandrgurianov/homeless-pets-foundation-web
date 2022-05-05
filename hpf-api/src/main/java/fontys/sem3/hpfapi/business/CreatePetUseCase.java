package fontys.sem3.hpfapi.business;

import fontys.sem3.hpfapi.dto.CreatePetRequestDTO;
import fontys.sem3.hpfapi.dto.CreatePetResponseDTO;

import javax.transaction.Transactional;

public interface CreatePetUseCase {
    @Transactional
    CreatePetResponseDTO createPet(CreatePetRequestDTO request);
}
