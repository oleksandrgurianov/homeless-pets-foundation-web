package fontys.sem3.hpfapi.business.impl;

import fontys.sem3.hpfapi.business.GetPetPicturesUseCase;
import fontys.sem3.hpfapi.dto.GetPetPicturesRequestDTO;
import fontys.sem3.hpfapi.dto.GetPetPicturesResponseDTO;
import fontys.sem3.hpfapi.dto.PetPictureDTO;
import fontys.sem3.hpfapi.repository.PetPictureRepository;
import fontys.sem3.hpfapi.repository.entity.PetPicture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetPetPicturesUseCaseImpl implements GetPetPicturesUseCase {
    private PetPictureRepository petPictureRepository;

    @Override
    public GetPetPicturesResponseDTO getPetPictures(GetPetPicturesRequestDTO request) {
        List<PetPicture> results = petPictureRepository.findAllByPetId(request.getPetId());
        final GetPetPicturesResponseDTO response = new GetPetPicturesResponseDTO();
        List<PetPictureDTO> petPicturesDTO = results
                .stream()
                .map(PetPictureDTOConverter::convertToDTO)
                .toList();
        response.setPetPictures(petPicturesDTO);
        return response;
    }
}

