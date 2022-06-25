package fontys.sem3.hpfapi.business.petPicture.impl;

import fontys.sem3.hpfapi.business.exception.UnauthorizedDataAccessException;
import fontys.sem3.hpfapi.business.petPicture.GetPetPicturesUseCase;
import fontys.sem3.hpfapi.business.converter.PetPictureDTOConverter;
import fontys.sem3.hpfapi.dto.login.AccessTokenDTO;
import fontys.sem3.hpfapi.dto.petPicture.GetPetPicturesRequestDTO;
import fontys.sem3.hpfapi.dto.petPicture.GetPetPicturesResponseDTO;
import fontys.sem3.hpfapi.dto.petPicture.PetPictureDTO;
import fontys.sem3.hpfapi.repository.PetPictureRepository;
import fontys.sem3.hpfapi.repository.entity.PetPicture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetPetPicturesUseCaseImpl implements GetPetPicturesUseCase {
    private PetPictureRepository petPictureRepository;
    private AccessTokenDTO requestAccessToken;

    @Override
    public GetPetPicturesResponseDTO getPetPictures(GetPetPicturesRequestDTO request) {
        if (requestAccessToken.hasRole("ADMIN") || requestAccessToken.hasRole("CUST")) {
            List<PetPicture> results = petPictureRepository.findAllByPetId(request.getPetId());
            final GetPetPicturesResponseDTO response = new GetPetPicturesResponseDTO();
            List<PetPictureDTO> petPicturesDTO = results
                    .stream()
                    .map(PetPictureDTOConverter::convertToDTO)
                    .toList();
            response.setPetPictures(petPicturesDTO);
            return response;
        } else {
            throw new UnauthorizedDataAccessException("ACCESS_DENIED");
        }
    }
}

