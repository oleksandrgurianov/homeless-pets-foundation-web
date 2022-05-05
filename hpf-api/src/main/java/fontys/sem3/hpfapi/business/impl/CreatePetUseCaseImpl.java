package fontys.sem3.hpfapi.business.impl;

import fontys.sem3.hpfapi.business.CreatePetUseCase;
import fontys.sem3.hpfapi.business.CustomerIdValidator;
import fontys.sem3.hpfapi.dto.CreatePetRequestDTO;
import fontys.sem3.hpfapi.dto.CreatePetResponseDTO;
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
        Pet newPet = Pet.builder()
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
