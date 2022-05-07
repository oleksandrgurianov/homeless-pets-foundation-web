package fontys.sem3.hpfapi.controller;

import fontys.sem3.hpfapi.business.GetAdministratorUseCase;
import fontys.sem3.hpfapi.dto.AdministratorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
@RequestMapping("/administrators")
@RequiredArgsConstructor
public class AdministratorController {
    private final GetAdministratorUseCase getAdministratorUseCase;

    @GetMapping("{id}")
    public ResponseEntity<AdministratorDTO> getAdministrator(@PathVariable(value = "id") final long id) {
        final Optional<AdministratorDTO> administratorOptional = getAdministratorUseCase.getAdministrator(id);

        if (administratorOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(administratorOptional.get());
    }
}
