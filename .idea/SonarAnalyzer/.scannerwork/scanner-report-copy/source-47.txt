package fontys.sem3.hpfapi.business.impl;

import fontys.sem3.hpfapi.dto.PetPictureDTO;
import fontys.sem3.hpfapi.repository.entity.PetPicture;

final class PetPictureDTOConverter {
    private PetPictureDTOConverter() { }

    public static PetPictureDTO convertToDTO(PetPicture petPicture) {
        return PetPictureDTO.builder()
                .id(petPicture.getId())
                .pet(PetDTOConverter.convertToDTO(petPicture.getPet()))
                .picture(petPicture.getPicture())
                .build();
    }
}
