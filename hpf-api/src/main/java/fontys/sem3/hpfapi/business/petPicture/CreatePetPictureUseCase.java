package fontys.sem3.hpfapi.business.petPicture;

import fontys.sem3.hpfapi.dto.petPicture.CreatePetPictureRequestDTO;
import fontys.sem3.hpfapi.dto.petPicture.CreatePetPictureResponseDTO;

public interface CreatePetPictureUseCase {
    CreatePetPictureResponseDTO createPetPicture(CreatePetPictureRequestDTO request);
}
