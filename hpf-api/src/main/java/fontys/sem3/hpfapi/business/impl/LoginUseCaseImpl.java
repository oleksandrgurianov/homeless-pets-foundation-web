package fontys.sem3.hpfapi.business.impl;

import fontys.sem3.hpfapi.business.AccessTokenEncoder;
import fontys.sem3.hpfapi.business.LoginUseCase;
import fontys.sem3.hpfapi.business.exception.InvalidCredentialsException;
import fontys.sem3.hpfapi.dto.AccessTokenDTO;
import fontys.sem3.hpfapi.dto.LoginRequestDTO;
import fontys.sem3.hpfapi.dto.LoginResponseDTO;
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
        return LoginResponseDTO.builder().accessToken(accessToken).build();
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
