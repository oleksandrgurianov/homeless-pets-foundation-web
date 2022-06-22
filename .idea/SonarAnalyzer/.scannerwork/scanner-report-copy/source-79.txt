package fontys.sem3.hpfapi.business;

import fontys.sem3.hpfapi.dto.GetPetsRequestDTO;
import fontys.sem3.hpfapi.dto.GetPetsResponseDTO;

public interface GetPetsUseCase {
    GetPetsResponseDTO getPets(GetPetsRequestDTO request);
}
