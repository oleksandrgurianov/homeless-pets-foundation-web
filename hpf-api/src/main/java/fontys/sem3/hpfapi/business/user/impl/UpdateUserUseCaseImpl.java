package fontys.sem3.hpfapi.business.user.impl;

import fontys.sem3.hpfapi.business.user.UpdateUserUseCase;
import fontys.sem3.hpfapi.business.exception.InvalidUserException;
import fontys.sem3.hpfapi.dto.user.UpdateUserAvatarRequestDTO;
import fontys.sem3.hpfapi.dto.user.UpdateUserDetailsRequestDTO;
import fontys.sem3.hpfapi.repository.UserRepository;
import fontys.sem3.hpfapi.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {
    private final UserRepository userRepository;

    @Transactional
    @Override
    public void updateUserAvatar(UpdateUserAvatarRequestDTO request) {
        Optional<User> userOptional = userRepository.findById(request.getId());

        if (userOptional.isEmpty()) {
            throw new InvalidUserException("USER_ID_INVALID");
        }

        User user = userOptional.get();
        user.setAvatar(request.getAvatar());
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void updateUserDetails(UpdateUserDetailsRequestDTO request) {
        Optional<User> userOptional = userRepository.findById(request.getId());

        if (userOptional.isEmpty()) {
            throw new InvalidUserException("USER_ID_INVALID");
        }

        User user = userOptional.get();
        validateEmail(request, user);
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(request.getPassword());
        userRepository.save(user);
    }

    private void validateEmail(UpdateUserDetailsRequestDTO request, User user) {
        User userWithSameEmail = userRepository.findByEmail(request.getEmail());
        if (userWithSameEmail != null && !userWithSameEmail.getId().equals(user.getId())) {
            throw new InvalidUserException("OTHER_USER_SAME_EMAIL");
        }
    }
}
