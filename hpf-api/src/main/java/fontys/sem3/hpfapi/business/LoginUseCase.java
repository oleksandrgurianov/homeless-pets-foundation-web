package fontys.sem3.hpfapi.business;

import fontys.sem3.hpfapi.dto.LoginRequestDTO;
import fontys.sem3.hpfapi.dto.LoginResponseDTO;

public interface LoginUseCase {
    LoginResponseDTO login(LoginRequestDTO loginRequest);
}
