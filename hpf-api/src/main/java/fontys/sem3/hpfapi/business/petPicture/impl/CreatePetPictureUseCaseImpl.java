package fontys.sem3.hpfapi.business.petPicture.impl;

import fontys.sem3.hpfapi.business.exception.UnauthorizedDataAccessException;
import fontys.sem3.hpfapi.business.petPicture.CreatePetPictureUseCase;
import fontys.sem3.hpfapi.business.validator.PetIdValidator;
import fontys.sem3.hpfapi.dto.login.AccessTokenDTO;
import fontys.sem3.hpfapi.dto.petPicture.CreatePetPictureRequestDTO;
import fontys.sem3.hpfapi.dto.petPicture.CreatePetPictureResponseDTO;
import fontys.sem3.hpfapi.repository.PetPictureRepository;
import fontys.sem3.hpfapi.repository.entity.Pet;
import fontys.sem3.hpfapi.repository.entity.PetPicture;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CreatePetPictureUseCaseImpl implements CreatePetPictureUseCase {
    private final PetPictureRepository petPictureRepository;
    private final PetIdValidator petIdValidator;
    private final AccessTokenDTO requestAccessToken;

    @Transactional
    @Override
    public CreatePetPictureResponseDTO createPetPicture(CreatePetPictureRequestDTO request) {
        if (requestAccessToken.hasRole("ADMIN")) {
            petIdValidator.validateId(request.getPetId());

            PetPicture newPetPicture = PetPicture.builder()
                    .pet(Pet.builder().id(request.getPetId()).build())
                    .picture(request.getPicture())
                    .build();

            PetPicture savedPetPicture = petPictureRepository.save(newPetPicture);

            return CreatePetPictureResponseDTO.builder()
                    .petPictureId(savedPetPicture.getId())
                    .build();
        } else {
            throw new UnauthorizedDataAccessException("ACCESS_DENIED");
        }
    }
}
