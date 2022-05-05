package fontys.sem3.hpfapi.business;

import fontys.sem3.hpfapi.dto.CreateUserRequestDTO;
import fontys.sem3.hpfapi.dto.CreateUserResponseDTO;

import javax.transaction.Transactional;

public interface CreateUserUseCase {
    @Transactional
    CreateUserResponseDTO createUser(CreateUserRequestDTO request);
}
