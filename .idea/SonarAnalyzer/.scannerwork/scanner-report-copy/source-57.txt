package fontys.sem3.hpfapi.business.impl;

import fontys.sem3.hpfapi.business.GetPetsUseCase;
import fontys.sem3.hpfapi.dto.GetPetsRequestDTO;
import fontys.sem3.hpfapi.dto.GetPetsResponseDTO;
import fontys.sem3.hpfapi.dto.PetDTO;
import fontys.sem3.hpfapi.repository.PetRepository;
import fontys.sem3.hpfapi.repository.entity.Pet;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.List;

@Service
@AllArgsConstructor
public class GetPetsUseCaseImpl implements GetPetsUseCase {
    private PetRepository petRepository;

    @Override
    public GetPetsResponseDTO getPets(GetPetsRequestDTO request) {
        List<Pet> results;

        if (StringUtils.hasText(request.getType())) {
            if (StringUtils.hasText(request.getBreed())) {
                if (BooleanUtils.isNotFalse(request.getAscending())) {
                    results = petRepository.findAllByTypeAndBreedContainingAndCustomerIdOrderByNameAsc(request.getType(), request.getBreed(), request.getCustomerId());
                } else {
                    results = petRepository.findAllByTypeAndBreedContainingAndCustomerIdOrderByNameDesc(request.getType(), request.getBreed(), request.getCustomerId());
                }
            } else {
                if (BooleanUtils.isNotFalse(request.getAscending())) {
                    results = petRepository.findAllByTypeAndCustomerIdOrderByNameAsc(request.getType(), request.getCustomerId());
                } else {
                    results = petRepository.findAllByTypeAndCustomerIdOrderByNameDesc(request.getType(), request.getCustomerId());
                }
            }
        } else {
            if (StringUtils.hasText(request.getBreed())) {
                if (BooleanUtils.isNotFalse(request.getAscending())) {
                    results = petRepository.findAllByBreedContainingAndCustomerIdOrderByNameAsc(request.getBreed(), request.getCustomerId());
                } else {
                    results = petRepository.findAllByBreedContainingAndCustomerIdOrderByNameDesc(request.getBreed(), request.getCustomerId());
                }
            } else {
                if (BooleanUtils.isNotFalse(request.getAscending())) {
                    results = petRepository.findAllByCustomerIdOrderByNameAsc(request.getCustomerId());
                } else {
                    results = petRepository.findAllByCustomerIdOrderByNameDesc(request.getCustomerId());
                }
            }
        }

        final GetPetsResponseDTO response = new GetPetsResponseDTO();
        List<PetDTO> petsDTO = results
                .stream()
                .map(PetDTOConverter::convertToDTO)
                .toList();
        response.setPets(petsDTO);
        return response;
    }
}
