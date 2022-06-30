package fontys.sem3.hpfapi.business.petPicture;

import fontys.sem3.hpfapi.business.exception.InvalidCustomerException;
import fontys.sem3.hpfapi.business.exception.InvalidPetException;
import fontys.sem3.hpfapi.business.exception.UnauthorizedDataAccessException;
import fontys.sem3.hpfapi.business.pet.impl.CreatePetUseCaseImpl;
import fontys.sem3.hpfapi.business.petPicture.impl.CreatePetPictureUseCaseImpl;
import fontys.sem3.hpfapi.business.validator.PetIdValidator;
import fontys.sem3.hpfapi.dto.donation.CreateDonationRequestDTO;
import fontys.sem3.hpfapi.dto.donation.CreateDonationResponseDTO;
import fontys.sem3.hpfapi.dto.login.AccessTokenDTO;
import fontys.sem3.hpfapi.dto.pet.CreatePetRequestDTO;
import fontys.sem3.hpfapi.dto.petPicture.CreatePetPictureRequestDTO;
import fontys.sem3.hpfapi.dto.petPicture.CreatePetPictureResponseDTO;
import fontys.sem3.hpfapi.repository.PetPictureRepository;
import fontys.sem3.hpfapi.repository.PetRepository;
import fontys.sem3.hpfapi.repository.entity.Customer;
import fontys.sem3.hpfapi.repository.entity.Donation;
import fontys.sem3.hpfapi.repository.entity.Pet;
import fontys.sem3.hpfapi.repository.entity.PetPicture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreatePetPictureImplTest {
    @Mock
    private PetPictureRepository petPictureRepositoryMock;

    @Mock
    private AccessTokenDTO requestAccessToken;

    @Mock
    private PetIdValidator petIdValidatorMock;

    @InjectMocks
    private CreatePetPictureUseCaseImpl createPetPictureUseCase;

    @Test
    void shouldThrowUnauthorizedDataAccessExceptionWhenUserRoleNotMatched() {
        when(requestAccessToken.hasRole("ADMIN"))
                .thenReturn(false);

        CreatePetPictureRequestDTO request = CreatePetPictureRequestDTO.builder().build();

        UnauthorizedDataAccessException unauthorizedDataAccessException = assertThrows(UnauthorizedDataAccessException.class,
                () -> createPetPictureUseCase.createPetPicture(request));

        assertEquals("ACCESS_DENIED", unauthorizedDataAccessException.getReason());

        verify(requestAccessToken).hasRole("ADMIN");
    }

    @Test
    void shouldThrowInvalidPetExceptionWhenPetIdInvalid() {
        when(requestAccessToken.hasRole("ADMIN"))
                .thenReturn(true);

        doThrow(InvalidPetException.class)
                .when(petIdValidatorMock).validateId(1L);

        CreatePetPictureRequestDTO request = CreatePetPictureRequestDTO.builder()
                .petId(1L)
                .picture("https://drive.google.com/uc?export=view&id=18G-f5b2AOrHx5DNUVxWSxXYz1TdCt4y8")
                .build();
        assertThrows(InvalidPetException.class, () -> createPetPictureUseCase.createPetPicture(request));

        verify(petIdValidatorMock).validateId(1L);
        verify(requestAccessToken).hasRole("ADMIN");
    }

    @Test
    void shouldCreatePetPicture() {
        when(requestAccessToken.hasRole("ADMIN"))
                .thenReturn(true);

        PetPicture expectedPetPictureToSave = PetPicture.builder()
                .pet(Pet.builder().id(1L).build())
                .picture("https://drive.google.com/uc?export=view&id=18G-f5b2AOrHx5DNUVxWSxXYz1TdCt4y8")
                .build();
        PetPicture savedPetPicture = PetPicture.builder()
                .id(1L)
                .pet(Pet.builder().id(1L).build())
                .picture("https://drive.google.com/uc?export=view&id=18G-f5b2AOrHx5DNUVxWSxXYz1TdCt4y8")
                .build();
        when(petPictureRepositoryMock.save(expectedPetPictureToSave)).thenReturn(savedPetPicture);

        CreatePetPictureRequestDTO request = CreatePetPictureRequestDTO.builder()
                .petId(1L)
                .picture("https://drive.google.com/uc?export=view&id=18G-f5b2AOrHx5DNUVxWSxXYz1TdCt4y8")
                .build();

        CreatePetPictureResponseDTO actualResponse = createPetPictureUseCase.createPetPicture(request);

        CreatePetPictureResponseDTO expectedResponse = CreatePetPictureResponseDTO.builder()
                .petPictureId(1L)
                .build();
        assertEquals(expectedResponse, actualResponse);

        verify(petIdValidatorMock).validateId(1L);
        verify(petPictureRepositoryMock).save(expectedPetPictureToSave);
        verify(requestAccessToken).hasRole("ADMIN");
    }
}
