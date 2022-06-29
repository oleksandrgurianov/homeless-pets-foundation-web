package fontys.sem3.hpfapi.controller;

import fontys.sem3.hpfapi.business.pet.*;
import fontys.sem3.hpfapi.configuration.security.isauthenticated.IsAuthenticated;
import fontys.sem3.hpfapi.dto.pet.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/pets")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
public class PetController {
    private final CreatePetUseCase createPetUseCase;
    private final DeletePetUseCase deletePetUseCase;
    private final GetPetsUseCase getPetsUseCase;
    private final GetPetUseCase getPetUseCase;
    private final UpdatePetUseCase updatePetUseCase;

    @IsAuthenticated
    @RolesAllowed({"ADMIN"})
    @PostMapping()
    public ResponseEntity<CreatePetResponseDTO> createPet(@RequestBody @Valid CreatePetRequestDTO request) {
        CreatePetResponseDTO response = createPetUseCase.createPet(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @IsAuthenticated
    @RolesAllowed({"ADMIN"})
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePet(@PathVariable int id) {
        deletePetUseCase.deletePet(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("categories/{type}")
    public ResponseEntity<GetPetsResponseDTO> getPets(@PathVariable("type") String type, @RequestParam(value = "userId", required = false) Long userId) {
        GetPetsRequestDTO request = new GetPetsRequestDTO();
        request.setType(type);
        request.setUserId(userId);
        return ResponseEntity.ok(getPetsUseCase.getPets(request));
    }

    @IsAuthenticated
    @RolesAllowed({"ADMIN", "CUST"})
    @GetMapping("{id}")
    public ResponseEntity<PetDTO> getPet(@PathVariable(value = "id") final long id) {
        final Optional<PetDTO> petOptional = getPetUseCase.getPet(id);

        if (petOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(petOptional.get());
    }

    @IsAuthenticated
    @RolesAllowed({"CUST"})
    @PutMapping("{id}/customer")
    public ResponseEntity<PetDTO> updatePetCustomer(@PathVariable("id") long id, @RequestBody @Valid UpdatePetCustomerRequestDTO request) {
        request.setId(id);
        updatePetUseCase.updatePetCustomer(request);
        return ResponseEntity.noContent().build();
    }
}
