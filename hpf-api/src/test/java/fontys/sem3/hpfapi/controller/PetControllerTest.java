package fontys.sem3.hpfapi.controller;

import fontys.sem3.hpfapi.business.pet.*;
import fontys.sem3.hpfapi.dto.pet.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class PetControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreatePetUseCase createPetUseCase;

    @MockBean
    private DeletePetUseCase deletePetUseCase;

    @MockBean
    private GetPetsUseCase getPetsUseCase;

    @MockBean
    private GetPetUseCase getPetUseCase;

    @MockBean
    private UpdatePetUseCase updatePetUseCase;


    @Test
    @WithMockUser(username = "admin@hpf.com", roles = {"ADMIN"})
    void createPet_shouldReturn201_whenRequestIsValid() throws Exception {
        CreatePetRequestDTO expectedRequest = CreatePetRequestDTO.builder()
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
        when(createPetUseCase.createPet(expectedRequest))
                .thenReturn(CreatePetResponseDTO.builder()
                        .petId(1L)
                        .build());

        mockMvc.perform(post("/pets")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                    "icon": "https://drive.google.com/uc?export=view&id=18G-f5b2AOrHx5DNUVxWSxXYz1TdCt4y8",
                                    "type": "DOGS",
                                    "name": "Jamison",
                                    "breed": "Terrier Mix",
                                    "ageCategory": "Young",
                                    "gender": "Male",
                                    "size": "Large",
                                    "color": "Brown/Chocolate, White/Cream",
                                    "description": "Meet Jamison! He is a 2 year old terrier mix. This handsome guy just loves people and gets the happiest wiggly butt of excitement. Great on the leash and knows basic commands.",
                                    "adoptionFee": "250"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                        {
                            "petId": 1
                        }
                        """));

        verify(createPetUseCase).createPet(expectedRequest);
    }

    @Test
    @WithMockUser(username = "admin@hpf.com", roles = {"ADMIN"})
    void deletePet_shouldReturn204() throws Exception {
        mockMvc.perform(delete("/pets/1"))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(deletePetUseCase).deletePet(1L);
    }

    @Test
    @WithMockUser(username = "admin@hpf.com", roles = {"ADMIN"})
    void getPets_shouldReturn200WithPetsList1() throws Exception {
        GetPetsResponseDTO responseDTO = GetPetsResponseDTO.builder()
                .pets(List.of(
                        PetDTO.builder()
                                .id(1L)
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
        GetPetsRequestDTO request = GetPetsRequestDTO.builder()
                .type("DOGS")
                .build();
        when(getPetsUseCase.getPets(request)).thenReturn(responseDTO);

        mockMvc.perform(get("/pets/categories/DOGS"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {
                                "pets": [
                                    {
                                        "id": 1,
                                        "icon": "https://drive.google.com/uc?export=view&id=18G-f5b2AOrHx5DNUVxWSxXYz1TdCt4y8",
                                        "type": "DOGS",
                                        "name": "Jamison",
                                        "breed": "Terrier Mix",
                                        "ageCategory": "Young",
                                        "gender": "Male",
                                        "size": "Large",
                                        "color": "Brown/Chocolate, White/Cream",
                                        "description": "Meet Jamison! He is a 2 year old terrier mix. This handsome guy just loves people and gets the happiest wiggly butt of excitement. Great on the leash and knows basic commands.",
                                        "adoptionFee": 250.0
                                    },
                                    {
                                        "id": 2,
                                        "icon": "https://drive.google.com/uc?export=view&id=1XoV_FRuKxOh2yW81sV_vPzcX6TvLhyNC",
                                        "type": "DOGS",
                                        "name": "Mini",
                                        "breed": "Chihuahua",
                                        "ageCategory": "Senior",
                                        "gender": "Male",
                                        "size": "Small",
                                        "color": "Black, White/Cream",
                                        "description": "Mini is a sweet older gentleman at 8 years old looking for a quite home to spend his golden years. He enjoys a good belly rub and nap on the couch. He is very loyal to his person and would make someone a wonderful companion. Looking for a mellow guy with lots of love to give? Mini is your man!",
                                        "adoptionFee": 80.0
                                    }
                                ]
                            }
                        """));

        verify(getPetsUseCase).getPets(request);
    }

    @Test
    @WithMockUser(username = "cust1@gmail.com", roles = {"CUST"})
    void getPets_shouldReturn200WithPetsList2() throws Exception {
        GetPetsResponseDTO responseDTO = GetPetsResponseDTO.builder()
                .pets(List.of(
                        PetDTO.builder()
                                .id(1L)
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
        GetPetsRequestDTO request = GetPetsRequestDTO.builder()
                .type("DOGS")
                .build();
        when(getPetsUseCase.getPets(request)).thenReturn(responseDTO);

        mockMvc.perform(get("/pets/categories/DOGS"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {
                                "pets": [
                                    {
                                        "id": 1,
                                        "icon": "https://drive.google.com/uc?export=view&id=18G-f5b2AOrHx5DNUVxWSxXYz1TdCt4y8",
                                        "type": "DOGS",
                                        "name": "Jamison",
                                        "breed": "Terrier Mix",
                                        "ageCategory": "Young",
                                        "gender": "Male",
                                        "size": "Large",
                                        "color": "Brown/Chocolate, White/Cream",
                                        "description": "Meet Jamison! He is a 2 year old terrier mix. This handsome guy just loves people and gets the happiest wiggly butt of excitement. Great on the leash and knows basic commands.",
                                        "adoptionFee": 250.0
                                    },
                                    {
                                        "id": 2,
                                        "icon": "https://drive.google.com/uc?export=view&id=1XoV_FRuKxOh2yW81sV_vPzcX6TvLhyNC",
                                        "type": "DOGS",
                                        "name": "Mini",
                                        "breed": "Chihuahua",
                                        "ageCategory": "Senior",
                                        "gender": "Male",
                                        "size": "Small",
                                        "color": "Black, White/Cream",
                                        "description": "Mini is a sweet older gentleman at 8 years old looking for a quite home to spend his golden years. He enjoys a good belly rub and nap on the couch. He is very loyal to his person and would make someone a wonderful companion. Looking for a mellow guy with lots of love to give? Mini is your man!",
                                        "adoptionFee": 80.0
                                    }
                                ]
                            }
                        """));

        verify(getPetsUseCase).getPets(request);
    }

    @Test
    @WithMockUser(username = "admin@hpf.com", roles = {"ADMIN"})
    void getPet_shouldReturn200WithPet_whenPetFound1() throws Exception {
        PetDTO petDTO = PetDTO.builder()
                .id(1L)
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
        when(getPetUseCase.getPet(1L)).thenReturn(Optional.of(petDTO));

        mockMvc.perform(get("/pets/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                        {
                            "id": 1,
                            "icon": "https://drive.google.com/uc?export=view&id=18G-f5b2AOrHx5DNUVxWSxXYz1TdCt4y8",
                            "type": "DOGS",
                            "name": "Jamison",
                            "breed": "Terrier Mix",
                            "ageCategory": "Young",
                            "gender": "Male",
                            "size": "Large",
                            "color": "Brown/Chocolate, White/Cream",
                            "description": "Meet Jamison! He is a 2 year old terrier mix. This handsome guy just loves people and gets the happiest wiggly butt of excitement. Great on the leash and knows basic commands.",
                            "adoptionFee": 250.0
                        }
                        """));

        verify(getPetUseCase).getPet(1L);
    }

    @Test
    @WithMockUser(username = "cust1@gmail.com", roles = {"CUST"})
    void getPet_shouldReturn200WithPet_whenPetFound2() throws Exception {
        PetDTO petDTO = PetDTO.builder()
                .id(1L)
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
        when(getPetUseCase.getPet(1L)).thenReturn(Optional.of(petDTO));

        mockMvc.perform(get("/pets/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                        {
                            "id": 1,
                            "icon": "https://drive.google.com/uc?export=view&id=18G-f5b2AOrHx5DNUVxWSxXYz1TdCt4y8",
                            "type": "DOGS",
                            "name": "Jamison",
                            "breed": "Terrier Mix",
                            "ageCategory": "Young",
                            "gender": "Male",
                            "size": "Large",
                            "color": "Brown/Chocolate, White/Cream",
                            "description": "Meet Jamison! He is a 2 year old terrier mix. This handsome guy just loves people and gets the happiest wiggly butt of excitement. Great on the leash and knows basic commands.",
                            "adoptionFee": 250.0
                        }
                        """));

        verify(getPetUseCase).getPet(1L);
    }

    @Test
    @WithMockUser(username = "cust1@gmail.com", roles = {"CUST"})
    void updatePetCustomer_shouldReturn204() throws Exception {
        mockMvc.perform(put("/pets/1/customer")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                    "id": "1",
                                    "customerId": "1"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isNoContent());

        UpdatePetCustomerRequestDTO expectedRequest = UpdatePetCustomerRequestDTO.builder()
                .id(1L)
                .customerId(1L)
                .build();
        verify(updatePetUseCase).updatePetCustomer(expectedRequest);
    }
}
