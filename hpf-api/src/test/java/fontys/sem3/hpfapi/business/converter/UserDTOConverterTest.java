package fontys.sem3.hpfapi.business.converter;

import fontys.sem3.hpfapi.dto.user.UserDTO;
import fontys.sem3.hpfapi.repository.entity.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserDTOConverterTest {
    @Test
    void shouldConvertAllUserFieldsToDTO() {
        User userToBeConverted = User.builder()
                .id(1L)
                .avatar("https://drive.google.com/uc?export=view&id=1_dkDl3VGEjdKqyO900DmdmBGg3DbaVVo")
                .fullName("Manna Barnhoorn")
                .email("admin@hpf.com")
                .phoneNumber("0641261843")
                .password("password")
                .role("ADMIN")
                .build();

        UserDTO actualDTO = UserDTOConverter.convertToDTO(userToBeConverted);

        UserDTO expectedDTO = UserDTO.builder()
                .id(1L)
                .avatar("https://drive.google.com/uc?export=view&id=1_dkDl3VGEjdKqyO900DmdmBGg3DbaVVo")
                .fullName("Manna Barnhoorn")
                .email("admin@hpf.com")
                .phoneNumber("0641261843")
                .password("password")
                .roles(List.of("ADMIN"))
                .build();

        assertEquals(expectedDTO, actualDTO);
    }
}
