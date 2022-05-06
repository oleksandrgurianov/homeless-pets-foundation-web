package fontys.sem3.hpfapi.business;

import fontys.sem3.hpfapi.dto.CreateUserRequestDTO;
import fontys.sem3.hpfapi.dto.CreateUserResponseDTO;

public interface CreateUserUseCase {
    CreateUserResponseDTO createUser(CreateUserRequestDTO request);
}
