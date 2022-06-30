package fontys.sem3.hpfapi.business.pet;

import fontys.sem3.hpfapi.business.exception.InvalidPetException;
import fontys.sem3.hpfapi.business.exception.UnauthorizedDataAccessException;
import fontys.sem3.hpfapi.business.pet.impl.CreatePetUseCaseImpl;
import fontys.sem3.hpfapi.dto.login.AccessTokenDTO;
import fontys.sem3.hpfapi.dto.pet.CreatePetRequestDTO;
import fontys.sem3.hpfapi.dto.pet.CreatePetResponseDTO;
import fontys.sem3.hpfapi.repository.PetRepository;
import fontys.sem3.hpfapi.repository.entity.Pet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreatePetUseCaseImplTest {
    @Mock
    private PetRepository petRepositoryMock;

    @Mock
    private AccessTokenDTO requestAccessToken;

    @InjectMocks
    private CreatePetUseCaseImpl createPetUseCase;

    @Test
    void shouldThrowUnauthorizedDataAccessExceptionWhenUserRoleNotMatched() {
        when(requestAccessToken.hasRole("ADMIN"))
                .thenReturn(false);

        CreatePetRequestDTO request = CreatePetRequestDTO.builder().build();

        UnauthorizedDataAccessException unauthorizedDataAccessException = assertThrows(UnauthorizedDataAccessException.class,
                () -> createPetUseCase.createPet(request));

        assertEquals("ACCESS_DENIED", unauthorizedDataAccessException.getReason());

        verify(requestAccessToken).hasRole("ADMIN");
    }

    @Test
    void shouldThrowInvalidPetExceptionWhenPetAlreadyExists() {
        when(requestAccessToken.hasRole("ADMIN"))
                .thenReturn(true);
        when(petRepositoryMock.existsByTypeAndNameAndBreed("DOGS", "Jamison", "Terrier Mix"))
                .thenReturn(true);

        CreatePetRequestDTO request = CreatePetRequestDTO.builder()
                .icon("https://drive.google.com/uc?export=view&id=18G-f5b2AOrHx5DNUVxWSxXYz1TdCt4y8")
                .type("DOGS")
                .name("Jamison")
                .breed("Terrier Mix")
                .ageCategory("Young")
                .gender("Male")
                .size("Large")
                .color("Brown/Chocolate, White/Cream")
                .description("Meet Jamison! He is a 2 year old terrier mix. This handsome guy just loves people and gets the happiest wiggly butt of excitement. Great on the leash and knows basic commands.")
                .adoptionFee(250d)
                .build();

        assertThrows(InvalidPetException.class, () -> createPetUseCase.createPet(request));

        verify(requestAccessToken).hasRole("ADMIN");
        verify(petRepositoryMock).existsByTypeAndNameAndBreed("DOGS", "Jamison", "Terrier Mix");
    }

    @Test
    void shouldSaveNewPet() {
        when(requestAccessToken.hasRole("ADMIN"))
                .thenReturn(true);
        when(petRepositoryMock.existsByTypeAndNameAndBreed("DOGS", "Jamison", "Terrier Mix"))
                .thenReturn(false);

        Pet expectedPetToSave = Pet.builder()
                .icon("https://drive.google.com/uc?export=view&id=18G-f5b2AOrHx5DNUVxWSxXYz1TdCt4y8")
                .type("DOGS")
                .name("Jamison")
                .breed("Terrier Mix")
                .ageCategory("Young")
                .gender("Male")
                .size("Large")
                .color("Brown/Chocolate, White/Cream")
                .description("Meet Jamison! He is a 2 year old terrier mix. This handsome guy just loves people and gets the happiest wiggly butt of excitement. Great on the leash and knows basic commands.")
                .adoptionFee(250d)
                .build();
        Pet savedPet = Pet.builder()
                .id(1L)
                .customer(null)
                .icon("https://drive.google.com/uc?export=view&id=18G-f5b2AOrHx5DNUVxWSxXYz1TdCt4y8")
                .type("DOGS")
                .name("Jamison")
                .breed("Terrier Mix")
                .ageCategory("Young")
                .gender("Male")
                .size("Large")
                .color("Brown/Chocolate, White/Cream")
                .description("Meet Jamison! He is a 2 year old terrier mix. This handsome guy just loves people and gets the happiest wiggly butt of excitement. Great on the leash and knows basic commands.")
                .adoptionFee(250d)
                .build();
        when(petRepositoryMock.save(expectedPetToSave)).thenReturn(savedPet);

        CreatePetRequestDTO request = CreatePetRequestDTO.builder()
                .icon("https://drive.google.com/uc?export=view&id=18G-f5b2AOrHx5DNUVxWSxXYz1TdCt4y8")
                .type("DOGS")
                .name("Jamison")
                .breed("Terrier Mix")
                .ageCategory("Young")
                .gender("Male")
                .size("Large")
                .color("Brown/Chocolate, White/Cream")
                .description("Meet Jamison! He is a 2 year old terrier mix. This handsome guy just loves people and gets the happiest wiggly butt of excitement. Great on the leash and knows basic commands.")
                .adoptionFee(250d)
                .build();

        CreatePetResponseDTO actualResponse = createPetUseCase.createPet(request);

        CreatePetResponseDTO expectedResponse = CreatePetResponseDTO.builder()
                .petId(1L)
                .build();

        assertEquals(expectedResponse, actualResponse);
        verify(requestAccessToken).hasRole("ADMIN");
        verify(petRepositoryMock).existsByTypeAndNameAndBreed("DOGS", "Jamison", "Terrier Mix");
        verify(petRepositoryMock).save(expectedPetToSave);
    }
}
