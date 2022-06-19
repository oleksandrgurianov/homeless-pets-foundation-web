package fontys.sem3.hpfapi.business.pet.impl;

import fontys.sem3.hpfapi.business.pet.GetPetUseCase;
import fontys.sem3.hpfapi.business.converter.PetDTOConverter;
import fontys.sem3.hpfapi.dto.pet.PetDTO;
import fontys.sem3.hpfapi.repository.PetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetPetUseCaseImpl implements GetPetUseCase {
    private PetRepository petRepository;

    @Override
    public Optional<PetDTO> getPet(long petId) {
        return petRepository.findById(petId).map(PetDTOConverter::convertToDTO);
    }
}
