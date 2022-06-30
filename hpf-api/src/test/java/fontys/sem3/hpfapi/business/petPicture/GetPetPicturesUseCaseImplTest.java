package fontys.sem3.hpfapi.business.petPicture;

import fontys.sem3.hpfapi.business.exception.UnauthorizedDataAccessException;
import fontys.sem3.hpfapi.business.petPicture.impl.GetPetPicturesUseCaseImpl;
import fontys.sem3.hpfapi.dto.login.AccessTokenDTO;
import fontys.sem3.hpfapi.dto.petPicture.GetPetPicturesRequestDTO;
import fontys.sem3.hpfapi.dto.petPicture.GetPetPicturesResponseDTO;
import fontys.sem3.hpfapi.dto.petPicture.PetPictureDTO;
import fontys.sem3.hpfapi.repository.PetPictureRepository;
import fontys.sem3.hpfapi.repository.entity.Pet;
import fontys.sem3.hpfapi.repository.entity.PetPicture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetPetPicturesUseCaseImplTest {
    @Mock
    private PetPictureRepository petPictureRepositoryMock;

    @Mock
    private AccessTokenDTO requestAccessToken;

    @InjectMocks
    private GetPetPicturesUseCaseImpl getPetPicturesUseCase;

    @Test
    void shouldThrowUnauthorizedDataAccessExceptionWhenUserRoleNotMatched() {
        when(requestAccessToken.hasRole("ADMIN"))
                .thenReturn(false);
        when(requestAccessToken.hasRole("CUST"))
                .thenReturn(false);

        GetPetPicturesRequestDTO request = GetPetPicturesRequestDTO.builder().build();

        UnauthorizedDataAccessException unauthorizedDataAccessException = assertThrows(UnauthorizedDataAccessException.class,
                () -> getPetPicturesUseCase.getPetPictures(request));

        assertEquals("ACCESS_DENIED", unauthorizedDataAccessException.getReason());

        verify(requestAccessToken).hasRole("ADMIN");
        verify(requestAccessToken).hasRole("CUST");
    }

    @Test
    void shouldReturnPetPicturesFilteredByPetId1() {
        when(petPictureRepositoryMock.findAllByPetId(1L))
                .thenReturn(List.of(
                        PetPicture.builder()
                                .id(1L)
                                .pet(Pet.builder().id(1L).build())
                                .picture("https://drive.google.com/uc?export=view&id=1MoTatuPI6iXDQkaIGfc-sSLqUaCSqsPR")
                                .build(),
                        PetPicture.builder()
                                .id(2L)
                                .pet(Pet.builder().id(1L).build())
                                .picture("https://drive.google.com/uc?export=view&id=12GFX_v0jKG9B21Ivd2iEjzkKLffLqKEg")
                                .build()
                ));

        when(requestAccessToken.hasRole("ADMIN"))
                .thenReturn(true);

        GetPetPicturesResponseDTO actualResponse = getPetPicturesUseCase.getPetPictures(GetPetPicturesRequestDTO.builder()
                .petId(1L)
                .build());

        GetPetPicturesResponseDTO expectedResponse = GetPetPicturesResponseDTO.builder()
                .petPictures(List.of(
                        PetPictureDTO.builder()
                                .id(1L)
                                .picture("https://drive.google.com/uc?export=view&id=1MoTatuPI6iXDQkaIGfc-sSLqUaCSqsPR")
                                .build(),
                        PetPictureDTO.builder()
                                .id(2L)
                                .picture("https://drive.google.com/uc?export=view&id=12GFX_v0jKG9B21Ivd2iEjzkKLffLqKEg")
                                .build()
                ))
                .build();
        assertEquals(expectedResponse, actualResponse);

        verify(petPictureRepositoryMock).findAllByPetId(1L);
        verify(requestAccessToken).hasRole("ADMIN");
    }

    @Test
    void shouldReturnPetPicturesFilteredByPetId2() {
        when(petPictureRepositoryMock.findAllByPetId(1L))
                .thenReturn(List.of(
                        PetPicture.builder()
                                .id(1L)
                                .pet(Pet.builder().id(1L).build())
                                .picture("https://drive.google.com/uc?export=view&id=1MoTatuPI6iXDQkaIGfc-sSLqUaCSqsPR")
                                .build(),
                        PetPicture.builder()
                                .id(2L)
                                .pet(Pet.builder().id(1L).build())
                                .picture("https://drive.google.com/uc?export=view&id=12GFX_v0jKG9B21Ivd2iEjzkKLffLqKEg")
                                .build()
                ));

        when(requestAccessToken.hasRole("ADMIN"))
                .thenReturn(false);
        when(requestAccessToken.hasRole("CUST"))
                .thenReturn(true);

        GetPetPicturesResponseDTO actualResponse = getPetPicturesUseCase.getPetPictures(GetPetPicturesRequestDTO.builder()
                .petId(1L)
                .build());

        GetPetPicturesResponseDTO expectedResponse = GetPetPicturesResponseDTO.builder()
                .petPictures(List.of(
                        PetPictureDTO.builder()
                                .id(1L)
                                .picture("https://drive.google.com/uc?export=view&id=1MoTatuPI6iXDQkaIGfc-sSLqUaCSqsPR")
                                .build(),
                        PetPictureDTO.builder()
                                .id(2L)
                                .picture("https://drive.google.com/uc?export=view&id=12GFX_v0jKG9B21Ivd2iEjzkKLffLqKEg")
                                .build()
                ))
                .build();
        assertEquals(expectedResponse, actualResponse);

        verify(petPictureRepositoryMock).findAllByPetId(1L);
        verify(requestAccessToken).hasRole("ADMIN");
        verify(requestAccessToken).hasRole("CUST");
    }
}
