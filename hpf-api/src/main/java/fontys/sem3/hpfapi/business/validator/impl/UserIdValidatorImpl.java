package fontys.sem3.hpfapi.business.validator.impl;

import fontys.sem3.hpfapi.business.validator.UserIdValidator;
import fontys.sem3.hpfapi.business.exception.InvalidUserException;
import fontys.sem3.hpfapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserIdValidatorImpl implements UserIdValidator {
    private final UserRepository userRepository;

    @Override
    public void validateId(Long userId) {
        if (userId == null || !userRepository.existsById(userId)) {
            throw new InvalidUserException();
        }
    }
}
