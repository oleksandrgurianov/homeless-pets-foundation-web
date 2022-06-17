package fontys.sem3.hpfapi.controller;

import fontys.sem3.hpfapi.business.*;
import fontys.sem3.hpfapi.configuration.security.isauthenticated.IsAuthenticated;
import fontys.sem3.hpfapi.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
public class UserController {
    private final CreateUserUseCase createUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final GetUserUseCase getUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;

    @PostMapping()
    public ResponseEntity<CreateUserResponseDTO> createUser(@RequestBody @Valid CreateUserRequestDTO request) {
        CreateUserResponseDTO response = createUserUseCase.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_CUST"})
    @DeleteMapping("{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
        deleteUserUseCase.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_CUST"})
    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable(value = "id") final long id) {
        final Optional<UserDTO> userOptional = getUserUseCase.getUser(id);

        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(userOptional.get());
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_CUST"})
    @PutMapping("{id}/avatar")
    public ResponseEntity<UserDTO> updateUserAvatar(@PathVariable("id") long id, @RequestBody @Valid UpdateUserAvatarRequestDTO request) {
        request.setId(id);
        updateUserUseCase.updateUserAvatar(request);
        return ResponseEntity.noContent().build();
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_CUST"})
    @PutMapping("{id}/details")
    public ResponseEntity<UserDTO> updateUserDetails(@PathVariable("id") long id, @RequestBody @Valid UpdateUserDetailsRequestDTO request) {
        request.setId(id);
        updateUserUseCase.updateUserDetails(request);
        return ResponseEntity.noContent().build();
    }
}