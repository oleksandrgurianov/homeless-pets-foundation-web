package fontys.sem3.hpfapi.business.pet.impl;

import fontys.sem3.hpfapi.business.pet.CreatePetUseCase;
import fontys.sem3.hpfapi.business.exception.InvalidPetException;
import fontys.sem3.hpfapi.dto.pet.CreatePetRequestDTO;
import fontys.sem3.hpfapi.dto.pet.CreatePetResponseDTO;
import fontys.sem3.hpfapi.repository.PetRepository;
import fontys.sem3.hpfapi.repository.entity.Pet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CreatePetUseCaseImpl implements CreatePetUseCase {
    private final PetRepository petRepository;

    @Transactional
    @Override
    public CreatePetResponseDTO createPet(CreatePetRequestDTO request) {
        if (petRepository.existsByTypeAndNameAndBreed(request.getType(), request.getName(), request.getBreed())) {
            throw new InvalidPetException("PET_DUPLICATED");
        }

        Pet newPet = Pet.builder()
                .type(request.getIcon())
                .type(request.getType())
                .name(request.getName())
                .breed(request.getBreed())
                .ageCategory(request.getAgeCategory())
                .gender(request.getGender())
                .size(request.getSize())
                .color(request.getColor())
                .description(request.getDescription())
                .adoptionFee(request.getAdoptionFee())
                .build();

        Pet savedPet = petRepository.save(newPet);

        return CreatePetResponseDTO.builder()
                .petId(savedPet.getId())
                .build();
    }
}
