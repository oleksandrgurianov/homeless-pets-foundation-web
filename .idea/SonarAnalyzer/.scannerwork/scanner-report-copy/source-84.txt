package fontys.sem3.hpfapi.business;

import fontys.sem3.hpfapi.dto.GetPetPicturesRequestDTO;
import fontys.sem3.hpfapi.dto.GetPetPicturesResponseDTO;

public interface GetPetPicturesUseCase {
    GetPetPicturesResponseDTO getPetPictures(GetPetPicturesRequestDTO request);
}
