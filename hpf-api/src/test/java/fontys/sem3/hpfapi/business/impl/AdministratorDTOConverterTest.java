package fontys.sem3.hpfapi.business.impl;

import fontys.sem3.hpfapi.dto.AdministratorDTO;
import fontys.sem3.hpfapi.dto.UserDTO;
import fontys.sem3.hpfapi.repository.entity.Administrator;
import fontys.sem3.hpfapi.repository.entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdministratorDTOConverterTest {
    @Test
    void shouldConvertAllAdministratorFieldsToDTO() {
        Administrator administratorToBeConverted = Administrator.builder()
                .id(1L)
                .user(User.builder()
                        .id(1L)
                        .avatar("https://drive.google.com/uc?export=view&id=1_dkDl3VGEjdKqyO900DmdmBGg3DbaVVo")
                        .fullName("Manna Barnhoorn")
                        .email("admin@hpf.com")
                        .phoneNumber("0641261843")
                        .password("password")
                        .role("ADMIN")
                        .build())
                .jobPosition("Client Service Representative")
                .build();

        AdministratorDTO actualDTO = AdministratorDTOConverter.convertToDTO(administratorToBeConverted);

        AdministratorDTO expectedDTO = AdministratorDTO.builder()
                .id(1L)
                .user(UserDTO.builder()
                        .id(1L)
                        .avatar("https://drive.google.com/uc?export=view&id=1_dkDl3VGEjdKqyO900DmdmBGg3DbaVVo")
                        .fullName("Manna Barnhoorn")
                        .email("admin@hpf.com")
                        .phoneNumber("0641261843")
                        .password("password")
                        .role("ADMIN")
                        .build())
                .jobPosition("Client Service Representative")
                .build();

        assertEquals(expectedDTO, actualDTO);
    }
}
