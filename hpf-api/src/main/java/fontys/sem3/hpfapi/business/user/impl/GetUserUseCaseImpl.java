package fontys.sem3.hpfapi.business.user.impl;

import fontys.sem3.hpfapi.business.exception.UnauthorizedDataAccessException;
import fontys.sem3.hpfapi.business.user.GetUserUseCase;
import fontys.sem3.hpfapi.business.converter.UserDTOConverter;
import fontys.sem3.hpfapi.dto.login.AccessTokenDTO;
import fontys.sem3.hpfapi.dto.user.UserDTO;
import fontys.sem3.hpfapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetUserUseCaseImpl implements GetUserUseCase {
    private UserRepository userRepository;
    private AccessTokenDTO requestAccessToken;

    @Override
    public Optional<UserDTO> getUser(long userId) {
        if (requestAccessToken.getUserId() != userId) {
            throw new UnauthorizedDataAccessException("USER_ID_NOT_FROM_LOGGED_IN_USER");
        }

        return userRepository.findById(userId).map(UserDTOConverter::convertToDTO);
    }
}
