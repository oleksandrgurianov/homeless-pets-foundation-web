package fontys.sem3.hpfapi.controller;

import fontys.sem3.hpfapi.business.LoginUseCase;
import fontys.sem3.hpfapi.dto.LoginRequestDTO;
import fontys.sem3.hpfapi.dto.LoginResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/logIn")
@RequiredArgsConstructor
public class LoginController {

    private final LoginUseCase loginUseCase;

    @PostMapping
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
        LoginResponseDTO loginResponseDTO = loginUseCase.login(loginRequestDTO);
        return ResponseEntity.ok(loginResponseDTO);
    }
}
