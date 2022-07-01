package fontys.sem3.hpfapi.controller;

import fontys.sem3.hpfapi.business.petPicture.CreatePetPictureUseCase;
import fontys.sem3.hpfapi.business.petPicture.GetPetPicturesUseCase;
import fontys.sem3.hpfapi.dto.petPicture.*;
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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class PetPictureControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreatePetPictureUseCase createPetPictureUseCase;

    @MockBean
    private GetPetPicturesUseCase getPetPicturesUseCase;

    @Test
    @WithMockUser(username = "admin@hpf.com", roles = {"ADMIN"})
    void createPetPicture_shouldReturn201_whenRequestIsValid() throws Exception {
        CreatePetPictureRequestDTO expectedRequest = CreatePetPictureRequestDTO.builder()
                .petId(1L)
                .picture("https://drive.google.com/uc?export=view&id=18G-f5b2AOrHx5DNUVxWSxXYz1TdCt4y8")
                .build();
        when(createPetPictureUseCase.createPetPicture(expectedRequest))
                .thenReturn(CreatePetPictureResponseDTO.builder()
                        .petPictureId(1L)
                        .build());

        mockMvc.perform(post("/pet_pictures")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                    "petId": "1",
                                    "picture": "https://drive.google.com/uc?export=view&id=18G-f5b2AOrHx5DNUVxWSxXYz1TdCt4y8"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                        {
                            "petPictureId": 1
                        }
                        """));

        verify(createPetPictureUseCase).createPetPicture(expectedRequest);
    }

    @Test
    @WithMockUser(username = "admin@hpf.com", roles = {"ADMIN"})
    void getPetPictures_shouldReturn200WithPetPicturesList1() throws Exception {
        GetPetPicturesResponseDTO responseDTO = GetPetPicturesResponseDTO.builder()
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
        GetPetPicturesRequestDTO request = GetPetPicturesRequestDTO.builder()
                .petId(1L)
                .build();
        when(getPetPicturesUseCase.getPetPictures(request)).thenReturn(responseDTO);

        mockMvc.perform(get("/pet_pictures/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {
                                "petPictures": [
                                    {
                                        "id": 1,
                                        "picture": "https://drive.google.com/uc?export=view&id=1MoTatuPI6iXDQkaIGfc-sSLqUaCSqsPR"
                                    },
                                    {
                                        "id": 2,
                                        "picture": "https://drive.google.com/uc?export=view&id=12GFX_v0jKG9B21Ivd2iEjzkKLffLqKEg"
                                    }
                                ]
                            }
                        """));

        verify(getPetPicturesUseCase).getPetPictures(request);
    }

    @Test
    @WithMockUser(username = "cust1@gmail.com", roles = {"CUST"})
    void getPetPictures_shouldReturn200WithPetPicturesList2() throws Exception {
        GetPetPicturesResponseDTO responseDTO = GetPetPicturesResponseDTO.builder()
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
        GetPetPicturesRequestDTO request = GetPetPicturesRequestDTO.builder()
                .petId(1L)
                .build();
        when(getPetPicturesUseCase.getPetPictures(request)).thenReturn(responseDTO);

        mockMvc.perform(get("/pet_pictures/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {
                                "petPictures": [
                                    {
                                        "id": 1,
                                        "picture": "https://drive.google.com/uc?export=view&id=1MoTatuPI6iXDQkaIGfc-sSLqUaCSqsPR"
                                    },
                                    {
                                        "id": 2,
                                        "picture": "https://drive.google.com/uc?export=view&id=12GFX_v0jKG9B21Ivd2iEjzkKLffLqKEg"
                                    }
                                ]
                            }
                        """));

        verify(getPetPicturesUseCase).getPetPictures(request);
    }
}
