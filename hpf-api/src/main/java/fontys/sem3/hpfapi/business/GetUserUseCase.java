package fontys.sem3.hpfapi.business;

import fontys.sem3.hpfapi.dto.UserDTO;

import java.util.Optional;

public interface GetUserUseCase {
    Optional<UserDTO> getUser(long userId);
}
