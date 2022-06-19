package fontys.sem3.hpfapi.business.petPicture;

import fontys.sem3.hpfapi.dto.petPicture.GetPetPicturesRequestDTO;
import fontys.sem3.hpfapi.dto.petPicture.GetPetPicturesResponseDTO;

public interface GetPetPicturesUseCase {
    GetPetPicturesResponseDTO getPetPictures(GetPetPicturesRequestDTO request);
}

