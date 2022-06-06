package fontys.sem3.hpfapi.business.impl;

import fontys.sem3.hpfapi.business.GetPetsUseCase;
import fontys.sem3.hpfapi.dto.GetPetsRequestDTO;
import fontys.sem3.hpfapi.dto.GetPetsResponseDTO;
import fontys.sem3.hpfapi.dto.PetDTO;
import fontys.sem3.hpfapi.repository.PetRepository;
import fontys.sem3.hpfapi.repository.entity.Pet;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
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
            if (StringUtils.hasText(request.getBreed())) {
                if (StringUtils.hasText(request.getSort())) {
                    switch (request.getSort()) {
                        case "nameAsc" -> results = petRepository.findAllByCustomerIsNullAndTypeAndBreedContainingOrderByNameAsc(request.getType(), request.getBreed());
                        case "nameDesc" -> results = petRepository.findAllByCustomerIsNullAndTypeAndBreedContainingOrderByNameDesc(request.getType(), request.getBreed());
                        case "adoptionFeeAsc" -> results = petRepository.findAllByCustomerIsNullAndTypeAndBreedContainingOrderByAdoptionFeeAsc(request.getType(), request.getBreed());
                        case "adoptionFeeDesc" -> results = petRepository.findAllByCustomerIsNullAndTypeAndBreedContainingOrderByAdoptionFeeDesc(request.getType(), request.getBreed());
                    }
                } else {
                    results = petRepository.findAllByCustomerIsNullAndTypeAndBreedContainingOrderByNameAsc(request.getType(), request.getBreed());
                }
            } else {
                if (StringUtils.hasText(request.getSort())) {
                    switch (request.getSort()) {
                        case "nameAsc" -> results = petRepository.findAllByCustomerIsNullAndTypeOrderByNameAsc(request.getType());
                        case "nameDesc" -> results = petRepository.findAllByCustomerIsNullAndTypeOrderByNameDesc(request.getType());
                        case "adoptionFeeAsc" -> results = petRepository.findAllByCustomerIsNullAndTypeOrderByAdoptionFeeAsc(request.getType());
                        case "adoptionFeeDesc" -> results = petRepository.findAllByCustomerIsNullAndTypeOrderByAdoptionFeeDesc(request.getType());
                    }
                } else {
                    results = petRepository.findAllByCustomerIsNullAndTypeOrderByNameAsc(request.getType());
                }
            }
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
