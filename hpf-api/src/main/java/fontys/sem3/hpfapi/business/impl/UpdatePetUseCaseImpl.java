package fontys.sem3.hpfapi.business.impl;

import fontys.sem3.hpfapi.business.CustomerIdValidator;
import fontys.sem3.hpfapi.business.UpdatePetUseCase;
import fontys.sem3.hpfapi.business.exception.InvalidPetException;
import fontys.sem3.hpfapi.dto.UpdatePetCustomerRequestDTO;
import fontys.sem3.hpfapi.dto.UpdatePetDetailsRequestDTO;
import fontys.sem3.hpfapi.repository.PetRepository;
import fontys.sem3.hpfapi.repository.entity.Customer;
import fontys.sem3.hpfapi.repository.entity.Pet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdatePetUseCaseImpl implements UpdatePetUseCase {
    private final PetRepository petRepository;
    private final CustomerIdValidator customerIdValidator;

    @Transactional
    @Override
    public void updatePetCustomer(UpdatePetCustomerRequestDTO request) {
        Optional<Pet> petOptional = petRepository.findById(request.getId());

        if (petOptional.isEmpty()) {
            throw new InvalidPetException("PET_ID_INVALID");
        }

        customerIdValidator.validateId(request.getCustomerId());
        Pet pet = petOptional.get();
        pet.setCustomer(Customer.builder().id(request.getCustomerId()).build());
        petRepository.save(pet);
    }

    @Transactional
    @Override
    public void updatePetDetails(UpdatePetDetailsRequestDTO request) {
        Optional<Pet> petOptional = petRepository.findById(request.getId());

        if (petOptional.isEmpty()) {
            throw new InvalidPetException("PET_ID_INVALID");
        }

        Pet pet = petOptional.get();
        validateTypeAndNameAndBreed(request, pet);
        pet.setType(request.getType());
        pet.setName(request.getName());
        pet.setBreed(request.getBreed());
        pet.setAgeCategory(request.getAgeCategory());
        pet.setGender(request.getGender());
        pet.setSize(request.getSize());
        pet.setColor(request.getColor());
        pet.setDescription(request.getDescription());
        pet.setAdoptionFee(request.getAdoptionFee());
        petRepository.save(pet);
    }

    private void validateTypeAndNameAndBreed(UpdatePetDetailsRequestDTO request, Pet pet) {
        Pet petWithSameTypeAndNameAndBreed = petRepository.findByTypeAndNameAndBreed(request.getType(), request.getName(), request.getBreed());
        if (petWithSameTypeAndNameAndBreed != null && !petWithSameTypeAndNameAndBreed.getId().equals(pet.getId())) {
            throw new InvalidPetException("OTHER_PET_SAME_TYPE_AND_NAME_AND_BREED");
        }
    }
}
