package fontys.sem3.hpfapi.business.impl;

import fontys.sem3.hpfapi.business.CreateUserUseCase;
import fontys.sem3.hpfapi.business.exception.UserAlreadyExistsException;
import fontys.sem3.hpfapi.dto.CreateUserRequestDTO;
import fontys.sem3.hpfapi.dto.CreateUserResponseDTO;
import fontys.sem3.hpfapi.repository.UserRepository;
import fontys.sem3.hpfapi.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {
    private final UserRepository userRepository;

    @Transactional
    @Override
    public CreateUserResponseDTO createUser(CreateUserRequestDTO request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException();
        }

        User newUser = User.builder()
                .avatar(request.getAvatar())
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(request.getPassword())
                .role(request.getRole())
                .build();

        User savedUser = userRepository.save(newUser);

        return CreateUserResponseDTO.builder()
                .userId(savedUser.getId())
                .build();
    }
}
