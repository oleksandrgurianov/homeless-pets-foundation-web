package fontys.sem3.hpfapi.controller;

import fontys.sem3.hpfapi.business.CreateUserUseCase;
import fontys.sem3.hpfapi.business.DeleteUserUseCase;
import fontys.sem3.hpfapi.business.UpdateUserUseCase;
import fontys.sem3.hpfapi.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
public class UserController {
    private final CreateUserUseCase createUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;

    @PostMapping()
    public ResponseEntity<CreateUserResponseDTO> createUser(@RequestBody @Valid CreateUserRequestDTO request) {
        CreateUserResponseDTO response = createUserUseCase.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int userId) {
        deleteUserUseCase.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}/avatar")
    public ResponseEntity<UserDTO> updateUserAvatar(@PathVariable("id") long id, @RequestBody @Valid UpdateUserAvatarRequestDTO request) {
        request.setId(id);
        updateUserUseCase.updateUserAvatar(request);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}/details")
    public ResponseEntity<UserDTO> updateUserDetails(@PathVariable("id") long id, @RequestBody @Valid UpdateUserDetailsRequestDTO request) {
        request.setId(id);
        updateUserUseCase.updateUserDetails(request);
        return ResponseEntity.noContent().build();
    }
}