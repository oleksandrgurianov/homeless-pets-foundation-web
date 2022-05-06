package fontys.sem3.hpfapi.business;

import fontys.sem3.hpfapi.dto.CreatePetPictureRequestDTO;
import fontys.sem3.hpfapi.dto.CreatePetPictureResponseDTO;

public interface CreatePetPictureUseCase {
    CreatePetPictureResponseDTO createPetPicture(CreatePetPictureRequestDTO request);
}
