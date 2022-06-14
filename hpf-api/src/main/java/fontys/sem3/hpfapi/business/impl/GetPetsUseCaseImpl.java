package fontys.sem3.hpfapi.business.impl;

import fontys.sem3.hpfapi.business.GetPetsUseCase;
import fontys.sem3.hpfapi.dto.GetPetsRequestDTO;
import fontys.sem3.hpfapi.dto.GetPetsResponseDTO;
import fontys.sem3.hpfapi.dto.PetDTO;
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
        List<Pet> results = null;

        if (request.getCustomerId() != null) {
            results = petRepository.findAllByCustomerIdOrderByNameAsc(request.getCustomerId());
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
