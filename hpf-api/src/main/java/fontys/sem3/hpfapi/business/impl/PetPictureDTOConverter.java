package fontys.sem3.hpfapi.business.impl;

import fontys.sem3.hpfapi.dto.PetPictureDTO;
import fontys.sem3.hpfapi.repository.entity.PetPicture;

public class PetPictureDTOConverter {
    private PetPictureDTOConverter() { }

    public static PetPictureDTO convertToDTO (PetPicture petPicture) {
        return PetPictureDTO.builder()
                .id(petPicture.getId())
                .picture(petPicture.getPicture())
                .build();
    }
}
