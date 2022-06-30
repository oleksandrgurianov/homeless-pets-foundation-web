package fontys.sem3.hpfapi.business.pet;

import fontys.sem3.hpfapi.business.exception.UnauthorizedDataAccessException;
import fontys.sem3.hpfapi.business.pet.impl.GetPetUseCaseImpl;
import fontys.sem3.hpfapi.dto.login.AccessTokenDTO;
import fontys.sem3.hpfapi.dto.pet.PetDTO;
import fontys.sem3.hpfapi.repository.PetRepository;
import fontys.sem3.hpfapi.repository.entity.Pet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetPetUseCaseImplTest {
    @Mock
    private PetRepository petRepositoryMock;

    @Mock
    private AccessTokenDTO requestAccessToken;

    @InjectMocks
    private GetPetUseCaseImpl getPetUseCase;

    @Test
    void shouldThrowUnauthorizedDataAccessExceptionWhenUserRoleNotMatched() {
        when(requestAccessToken.hasRole("ADMIN"))
                .thenReturn(false);
        when(requestAccessToken.hasRole("CUST"))
                .thenReturn(false);

        UnauthorizedDataAccessException unauthorizedDataAccessException = assertThrows(UnauthorizedDataAccessException.class,
                () -> getPetUseCase.getPet(1L));

        assertEquals("ACCESS_DENIED", unauthorizedDataAccessException.getReason());

        verify(requestAccessToken).hasRole("ADMIN");
        verify(requestAccessToken).hasRole("CUST");
    }

    @Test
    void shouldReturnPetIfFound1() {
        Pet pet = Pet.builder()
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
        when(petRepositoryMock.findByIdAndCustomerIsNull(1L))
                .thenReturn(Optional.of(pet));

        when(requestAccessToken.hasRole("ADMIN"))
                .thenReturn(true);

        Optional<PetDTO> petOptional = getPetUseCase.getPet(1L);
        PetDTO actualDTO = petOptional.orElseThrow();

        PetDTO expectedDTO = PetDTO.builder()
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
        assertEquals(expectedDTO, actualDTO);

        verify(petRepositoryMock).findByIdAndCustomerIsNull(1L);
        verify(requestAccessToken).hasRole("ADMIN");
    }

    @Test
    void shouldReturnPetIfFound2() {
        Pet pet = Pet.builder()
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
        when(petRepositoryMock.findByIdAndCustomerIsNull(1L))
                .thenReturn(Optional.of(pet));

        when(requestAccessToken.hasRole("ADMIN"))
                .thenReturn(false);
        when(requestAccessToken.hasRole("CUST"))
                .thenReturn(true);

        Optional<PetDTO> petOptional = getPetUseCase.getPet(1L);
        PetDTO actualDTO = petOptional.orElseThrow();

        PetDTO expectedDTO = PetDTO.builder()
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
        assertEquals(expectedDTO, actualDTO);

        verify(petRepositoryMock).findByIdAndCustomerIsNull(1L);
        verify(requestAccessToken).hasRole("ADMIN");
        verify(requestAccessToken).hasRole("CUST");
    }
}
