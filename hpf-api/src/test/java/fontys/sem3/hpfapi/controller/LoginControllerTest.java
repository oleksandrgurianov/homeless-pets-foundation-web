package fontys.sem3.hpfapi.controller;

import fontys.sem3.hpfapi.business.login.LoginUseCase;
import fontys.sem3.hpfapi.dto.login.LoginRequestDTO;
import fontys.sem3.hpfapi.dto.login.LoginResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoginUseCase loginUseCase;

    @Test
    void login_shouldReturn200_whenRequestIsValid() throws Exception {
        LoginRequestDTO expectedRequest = LoginRequestDTO.builder()
                .email("admin@hpf.com")
                .password("password")
                .build();
        when(loginUseCase.login(expectedRequest))
                .thenReturn(LoginResponseDTO.builder()
                        .accessToken("h22hhs")
                        .role("ADMIN")
                        .userId(1L)
                        .build());

        mockMvc.perform(post("/login")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                    "email": "admin@hpf.com",
                                    "password": "password"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "accessToken": "h22hhs",
                            "role": "ADMIN",
                            "userId": 1
                        }
                        """));

        verify(loginUseCase).login(expectedRequest);
    }
}
