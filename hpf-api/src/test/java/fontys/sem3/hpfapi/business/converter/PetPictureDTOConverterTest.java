package fontys.sem3.hpfapi.business.converter;

import fontys.sem3.hpfapi.dto.petPicture.PetPictureDTO;
import fontys.sem3.hpfapi.repository.entity.PetPicture;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PetPictureDTOConverterTest {
    @Test
    void shouldConvertAllPetPictureFieldsToDTO() {
        PetPicture petPictureToBeConverted = PetPicture.builder()
                .id(1L)
                .picture("https://drive.google.com/uc?export=view&id=1MoTatuPI6iXDQkaIGfc-sSLqUaCSqsPR")
                .build();

        PetPictureDTO actualDTO = PetPictureDTOConverter.convertToDTO(petPictureToBeConverted);

        PetPictureDTO expectedDTO = PetPictureDTO.builder()
                .id(1L)
                .picture("https://drive.google.com/uc?export=view&id=1MoTatuPI6iXDQkaIGfc-sSLqUaCSqsPR")
                .build();

        assertEquals(expectedDTO, actualDTO);
    }
}
