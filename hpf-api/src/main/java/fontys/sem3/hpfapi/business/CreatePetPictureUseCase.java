package fontys.sem3.hpfapi.business;

import fontys.sem3.hpfapi.dto.CreatePetPictureRequestDTO;
import fontys.sem3.hpfapi.dto.CreatePetPictureResponseDTO;

import javax.transaction.Transactional;

public interface CreatePetPictureUseCase {
    @Transactional
    CreatePetPictureResponseDTO createPetPicture(CreatePetPictureRequestDTO request);
}
