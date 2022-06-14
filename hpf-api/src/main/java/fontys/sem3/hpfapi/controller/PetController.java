package fontys.sem3.hpfapi.controller;

import fontys.sem3.hpfapi.business.*;
import fontys.sem3.hpfapi.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping()
    public ResponseEntity<CreatePetResponseDTO> createPet(@RequestBody @Valid CreatePetRequestDTO request) {
        CreatePetResponseDTO response = createPetUseCase.createPet(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePet(@PathVariable int id) {
        deletePetUseCase.deletePet(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("categories/{type}")
    public ResponseEntity<GetPetsResponseDTO> getPets(@PathVariable("type") String type, @RequestParam(value = "customerId", required = false) Long customerId) {
        GetPetsRequestDTO request = new GetPetsRequestDTO();
        request.setType(type);
        request.setCustomerId(customerId);
        return ResponseEntity.ok(getPetsUseCase.getPets(request));
    }

    @GetMapping("{id}")
    public ResponseEntity<PetDTO> getPet(@PathVariable(value = "id") final long id) {
        final Optional<PetDTO> petOptional = getPetUseCase.getPet(id);

        if (petOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(petOptional.get());
    }

    @PutMapping("{id}/customer")
    public ResponseEntity<PetDTO> updatePetCustomer(@PathVariable("id") long id, @RequestBody @Valid UpdatePetCustomerRequestDTO request) {
        request.setId(id);
        updatePetUseCase.updatePetCustomer(request);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}/details")
    public ResponseEntity<PetDTO> updatePetDetails(@PathVariable("id") long id, @RequestBody @Valid UpdatePetDetailsRequestDTO request) {
        request.setId(id);
        updatePetUseCase.updatePetDetails(request);
        return ResponseEntity.noContent().build();
    }
}
