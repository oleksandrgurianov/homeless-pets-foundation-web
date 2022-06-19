package fontys.sem3.hpfapi.business.user;

import fontys.sem3.hpfapi.dto.user.UserDTO;

import java.util.Optional;

public interface GetUserUseCase {
    Optional<UserDTO> getUser(long userId);
}
