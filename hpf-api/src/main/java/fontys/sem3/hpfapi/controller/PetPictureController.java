package fontys.sem3.hpfapi.controller;

import fontys.sem3.hpfapi.business.petPicture.CreatePetPictureUseCase;
import fontys.sem3.hpfapi.business.petPicture.GetPetPicturesUseCase;
import fontys.sem3.hpfapi.configuration.security.isauthenticated.IsAuthenticated;
import fontys.sem3.hpfapi.dto.petPicture.CreatePetPictureRequestDTO;
import fontys.sem3.hpfapi.dto.petPicture.CreatePetPictureResponseDTO;
import fontys.sem3.hpfapi.dto.petPicture.GetPetPicturesRequestDTO;
import fontys.sem3.hpfapi.dto.petPicture.GetPetPicturesResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequestMapping("/pet_pictures")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
public class PetPictureController {
    private final CreatePetPictureUseCase createPetPictureUseCase;
    private final GetPetPicturesUseCase getPetPicturesUseCase;

    @IsAuthenticated
    @RolesAllowed({"ADMIN"})
    @PostMapping()
    public ResponseEntity<CreatePetPictureResponseDTO> createPetPicture(@RequestBody @Valid CreatePetPictureRequestDTO request) {
        CreatePetPictureResponseDTO response = createPetPictureUseCase.createPetPicture(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @IsAuthenticated
    @RolesAllowed({"ADMIN", "CUST"})
    @GetMapping("{petId}")
    public ResponseEntity<GetPetPicturesResponseDTO> getPetPictures(@PathVariable(value = "petId") final long petId) {
        GetPetPicturesRequestDTO request = new GetPetPicturesRequestDTO();
        request.setPetId(petId);
        return ResponseEntity.ok(getPetPicturesUseCase.getPetPictures(request));
    }
}

