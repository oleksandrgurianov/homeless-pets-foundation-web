package fontys.sem3.hpfapi.business.pet.impl;

import fontys.sem3.hpfapi.business.pet.GetPetsUseCase;
import fontys.sem3.hpfapi.business.converter.PetDTOConverter;
import fontys.sem3.hpfapi.dto.pet.GetPetsRequestDTO;
import fontys.sem3.hpfapi.dto.pet.GetPetsResponseDTO;
import fontys.sem3.hpfapi.dto.pet.PetDTO;
import fontys.sem3.hpfapi.repository.PetRepository;
import fontys.sem3.hpfapi.repository.entity.Pet;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetPetsUseCaseImpl implements GetPetsUseCase {
    private PetRepository petRepository;

    @Override
    public GetPetsResponseDTO getPets(GetPetsRequestDTO request) {
        List<Pet> results;

        if (request.getUserId() != null) {
            results = petRepository.findAllByCustomerUserIdOrderByNameAsc(request.getUserId());
        } else {
            results = petRepository.findAllByCustomerIsNullAndTypeOrderByNameAsc(request.getType());
        }

        final GetPetsResponseDTO response = new GetPetsResponseDTO();
        assert results != null;

        List<PetDTO> petsDTO = results
                .stream()
                .map(PetDTOConverter::convertToDTO)
                .toList();

        response.setPets(petsDTO);
        return response;
    }
}
