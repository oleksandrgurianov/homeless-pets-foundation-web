package fontys.sem3.hpfapi.business.login;

import fontys.sem3.hpfapi.business.exception.InvalidCredentialsException;
import fontys.sem3.hpfapi.business.login.impl.LoginUseCaseImpl;
import fontys.sem3.hpfapi.dto.login.AccessTokenDTO;
import fontys.sem3.hpfapi.dto.login.LoginRequestDTO;
import fontys.sem3.hpfapi.dto.login.LoginResponseDTO;
import fontys.sem3.hpfapi.repository.UserRepository;
import fontys.sem3.hpfapi.repository.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginUseCaseImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AccessTokenEncoder accessTokenEncoder;

    @InjectMocks
    private LoginUseCaseImpl loginUseCase;

    @Test
    void shouldThrowInvalidCredentialsExceptionWhenUserDoesNotExist() {
        LoginRequestDTO login = LoginRequestDTO.builder()
                .email("admin@hpf.com")
                .password("password")
                .build();

        when(userRepository.findByEmail("admin@hpf.com"))
                .thenReturn(null);

        assertThrows(InvalidCredentialsException.class, () -> loginUseCase.login(login));

        verify(userRepository).findByEmail("admin@hpf.com");
    }

    @Test
    void shouldThrowInvalidCredentialsExceptionWhenEmailAndPasswordNotMatched() {
        User user = User.builder()
                .id(1L)
                .avatar("https://drive.google.com/uc?export=view&id=1_dkDl3VGEjdKqyO900DmdmBGg3DbaVVo")
                .fullName("Manna Barnhoorn")
                .email("admin@hpf.com")
                .phoneNumber("0641261843")
                .password("password")
                .role("ADMIN")
                .build();

        LoginRequestDTO login = LoginRequestDTO.builder()
                .email("admin@hpf.com")
                .password("password")
                .build();

        when(userRepository.findByEmail("admin@hpf.com"))
                .thenReturn(user);
        when(passwordEncoder.matches("password", "password"))
                .thenReturn(false);

        assertThrows(InvalidCredentialsException.class, () -> loginUseCase.login(login));

        verify(userRepository).findByEmail("admin@hpf.com");
        verify(passwordEncoder).matches("password", "password");
    }

    @Test
    void shouldReturnAccessToken() {
        User user = User.builder()
                .id(1L)
                .avatar("https://drive.google.com/uc?export=view&id=1_dkDl3VGEjdKqyO900DmdmBGg3DbaVVo")
                .fullName("Manna Barnhoorn")
                .email("admin@hpf.com")
                .phoneNumber("0641261843")
                .password("password")
                .role("ADMIN")
                .build();

        LoginRequestDTO login = LoginRequestDTO.builder()
                .email("admin@hpf.com")
                .password("password")
                .build();

        when(userRepository.findByEmail("admin@hpf.com"))
                .thenReturn(user);
        when(passwordEncoder.matches("password", "password"))
                .thenReturn(true);

        LoginResponseDTO actualResult = loginUseCase.login(login);

        String accessToken = accessTokenEncoder.encode(
                AccessTokenDTO.builder()
                        .subject(user.getEmail())
                        .roles(List.of(user.getRole()))
                        .userId(user.getId())
                        .build());
        LoginResponseDTO expectedResult = LoginResponseDTO.builder()
                .accessToken(accessToken)
                .role(user.getRole())
                .userId(user.getId())
                .build();

        assertEquals(expectedResult, actualResult);

        verify(userRepository).findByEmail("admin@hpf.com");
        verify(passwordEncoder).matches("password", "password");
    }
}