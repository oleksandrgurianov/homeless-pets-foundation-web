package fontys.sem3.hpfapi.business.login;

import fontys.sem3.hpfapi.dto.login.LoginRequestDTO;
import fontys.sem3.hpfapi.dto.login.LoginResponseDTO;

public interface LoginUseCase {
    LoginResponseDTO login(LoginRequestDTO loginRequest);
}
