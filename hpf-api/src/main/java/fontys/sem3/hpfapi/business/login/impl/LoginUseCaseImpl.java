package fontys.sem3.hpfapi.business.login.impl;

import fontys.sem3.hpfapi.business.login.AccessTokenEncoder;
import fontys.sem3.hpfapi.business.login.LoginUseCase;
import fontys.sem3.hpfapi.business.exception.InvalidCredentialsException;
import fontys.sem3.hpfapi.dto.login.AccessTokenDTO;
import fontys.sem3.hpfapi.dto.login.LoginRequestDTO;
import fontys.sem3.hpfapi.dto.login.LoginResponseDTO;
import fontys.sem3.hpfapi.repository.UserRepository;
import fontys.sem3.hpfapi.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginUseCaseImpl implements LoginUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenEncoder accessTokenEncoder;

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail());
        if (user == null) {
            throw new InvalidCredentialsException();
        }

        if (!matchesPassword(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        String accessToken = generateAccessToken(user);
        return LoginResponseDTO.builder()
                .accessToken(accessToken)
                .role(user.getRole())
                .userId(user.getId())
                .build();
    }

    private boolean matchesPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    private String generateAccessToken(User user) {
        return accessTokenEncoder.encode(
                AccessTokenDTO.builder()
                        .subject(user.getEmail())
                        .roles(List.of(user.getRole()))
                        .userId(user.getId())
                        .build());
    }

}
