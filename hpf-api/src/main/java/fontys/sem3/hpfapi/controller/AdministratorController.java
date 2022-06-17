package fontys.sem3.hpfapi.controller;

import fontys.sem3.hpfapi.business.GetAdministratorUseCase;
import fontys.sem3.hpfapi.configuration.security.isauthenticated.IsAuthenticated;
import fontys.sem3.hpfapi.dto.AdministratorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Optional;

@RestController
@RequestMapping("/administrators")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
public class AdministratorController {
    private final GetAdministratorUseCase getAdministratorUseCase;

    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    @GetMapping("{id}")
    public ResponseEntity<AdministratorDTO> getAdministrator(@PathVariable(value = "id") final long id) {
        final Optional<AdministratorDTO> administratorOptional = getAdministratorUseCase.getAdministrator(id);

        if (administratorOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(administratorOptional.get());
    }
}
