package fontys.sem3.hpfapi.business.impl;

import fontys.sem3.hpfapi.business.GetPetsUseCase;
import fontys.sem3.hpfapi.dto.GetPetsRequestDTO;
import fontys.sem3.hpfapi.dto.GetPetsResponseDTO;
import fontys.sem3.hpfapi.dto.PetDTO;
import fontys.sem3.hpfapi.repository.PetPictureRepository;
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
    private PetPictureRepository petPictureRepository;

    @Override
    public GetPetsResponseDTO getPets(GetPetsRequestDTO request) {
        List<Pet> results;

        if (request.getCustomerId() != null) {
            results = petRepository.findAllByCustomerIdOrderByNameAsc(request.getCustomerId());
        } else {
            if (StringUtils.hasText(request.getBreed())) {
                if (BooleanUtils.isNotFalse(request.getAscending())) {
                    results = petRepository.findAllByTypeAndBreedContainingAndAdoptedOrderByNameAsc(request.getType(), request.getBreed(), false);
                } else {
                    results = petRepository.findAllByTypeAndBreedContainingAndAdoptedOrderByNameDesc(request.getType(), request.getBreed(), false);
                }
            } else {
                if (BooleanUtils.isNotFalse(request.getAscending())) {
                    results = petRepository.findAllByTypeAndAdoptedOrderByNameAsc(request.getType(), false);
                } else {
                    results = petRepository.findAllByTypeAndAdoptedOrderByNameDesc(request.getType(), false);
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
