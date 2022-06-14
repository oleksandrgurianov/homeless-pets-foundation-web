package fontys.sem3.hpfapi.controller;

import fontys.sem3.hpfapi.business.CreatePetPictureUseCase;
import fontys.sem3.hpfapi.business.DeletePetPictureUseCase;
import fontys.sem3.hpfapi.business.GetPetPicturesUseCase;
import fontys.sem3.hpfapi.dto.CreatePetPictureRequestDTO;
import fontys.sem3.hpfapi.dto.CreatePetPictureResponseDTO;
import fontys.sem3.hpfapi.dto.GetPetPicturesRequestDTO;
import fontys.sem3.hpfapi.dto.GetPetPicturesResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/pet_pictures")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
public class PetPictureController {
    private final CreatePetPictureUseCase createPetPictureUseCase;
    private final DeletePetPictureUseCase deletePetPictureUseCase;
    private final GetPetPicturesUseCase getPetPicturesUseCase;

    @PostMapping()
    public ResponseEntity<CreatePetPictureResponseDTO> createPet(@RequestBody @Valid CreatePetPictureRequestDTO request) {
        CreatePetPictureResponseDTO response = createPetPictureUseCase.createPetPicture(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("{petPictureId}")
    public ResponseEntity<Void> deletePetPicture(@PathVariable int petPictureId) {
        deletePetPictureUseCase.deletePetPicture(petPictureId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<GetPetPicturesResponseDTO> getPetPictures(@RequestParam(value = "petId") Long petId) {
        GetPetPicturesRequestDTO request = new GetPetPicturesRequestDTO();
        request.setPetId(petId);
        return ResponseEntity.ok(getPetPicturesUseCase.getPetPictures(request));
    }
}

