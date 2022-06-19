package fontys.sem3.hpfapi.business.pet;

import fontys.sem3.hpfapi.dto.pet.GetPetsRequestDTO;
import fontys.sem3.hpfapi.dto.pet.GetPetsResponseDTO;

public interface GetPetsUseCase {
    GetPetsResponseDTO getPets(GetPetsRequestDTO request);
}
