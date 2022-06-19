package fontys.sem3.hpfapi.business.user;

import fontys.sem3.hpfapi.dto.user.CreateUserRequestDTO;
import fontys.sem3.hpfapi.dto.user.CreateUserResponseDTO;

public interface CreateUserUseCase {
    CreateUserResponseDTO createUser(CreateUserRequestDTO request);
}
