package fontys.sem3.hpfapi.business.pet;

import fontys.sem3.hpfapi.business.pet.impl.GetPetsUseCaseImpl;
import fontys.sem3.hpfapi.dto.pet.GetPetsRequestDTO;
import fontys.sem3.hpfapi.dto.pet.GetPetsResponseDTO;
import fontys.sem3.hpfapi.dto.pet.PetDTO;
import fontys.sem3.hpfapi.repository.PetRepository;
import fontys.sem3.hpfapi.repository.entity.Pet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetPetsUseCaseImplTest {
    @Mock
    private PetRepository petRepositoryMock;

    @InjectMocks
    private GetPetsUseCaseImpl getPetsUseCase;

    @Test
    void shouldReturnPetsFilteredByType() {
        when(petRepositoryMock.findAllByCustomerIsNullAndTypeOrderByNameAsc("DOGS"))
                .thenReturn(List.of(
                        Pet.builder()
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
                                .build(),
                        Pet.builder()
                                .id(2L)
                                .customer(null)
                                .icon("https://drive.google.com/uc?export=view&id=1XoV_FRuKxOh2yW81sV_vPzcX6TvLhyNC")
                                .type("DOGS")
                                .name("Mini")
                                .breed("Chihuahua")
                                .ageCategory("Senior")
                                .gender("Male")
                                .size("Small")
                                .color("Black, White/Cream")
                                .description("Mini is a sweet older gentleman at 8 years old looking for a quite home to spend his golden years. He enjoys a good belly rub and nap on the couch. He is very loyal to his person and would make someone a wonderful companion. Looking for a mellow guy with lots of love to give? Mini is your man!")
                                .adoptionFee(80d)
                                .build()
                ));

        GetPetsResponseDTO actualResponse = getPetsUseCase.getPets(GetPetsRequestDTO.builder()
                .type("DOGS")
                .build());

        GetPetsResponseDTO expectedResponse = GetPetsResponseDTO.builder()
                .pets(List.of(
                        PetDTO.builder()
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
                                .build(),
                        PetDTO.builder()
                                .id(2L)
                                .customer(null)
                                .icon("https://drive.google.com/uc?export=view&id=1XoV_FRuKxOh2yW81sV_vPzcX6TvLhyNC")
                                .type("DOGS")
                                .name("Mini")
                                .breed("Chihuahua")
                                .ageCategory("Senior")
                                .gender("Male")
                                .size("Small")
                                .color("Black, White/Cream")
                                .description("Mini is a sweet older gentleman at 8 years old looking for a quite home to spend his golden years. He enjoys a good belly rub and nap on the couch. He is very loyal to his person and would make someone a wonderful companion. Looking for a mellow guy with lots of love to give? Mini is your man!")
                                .adoptionFee(80d)
                                .build()
                ))
                .build();
        assertEquals(expectedResponse, actualResponse);

        verify(petRepositoryMock).findAllByCustomerIsNullAndTypeOrderByNameAsc("DOGS");
    }
}
