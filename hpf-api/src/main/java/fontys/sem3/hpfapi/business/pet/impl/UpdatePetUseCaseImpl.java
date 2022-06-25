package fontys.sem3.hpfapi.business.pet.impl;

import fontys.sem3.hpfapi.business.exception.UnauthorizedDataAccessException;
import fontys.sem3.hpfapi.business.validator.CustomerIdValidator;
import fontys.sem3.hpfapi.business.pet.UpdatePetUseCase;
import fontys.sem3.hpfapi.business.exception.InvalidPetException;
import fontys.sem3.hpfapi.dto.login.AccessTokenDTO;
import fontys.sem3.hpfapi.dto.pet.UpdatePetCustomerRequestDTO;
import fontys.sem3.hpfapi.dto.pet.UpdatePetDetailsRequestDTO;
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
    private final AccessTokenDTO requestAccessToken;

    @Transactional
    @Override
    public void updatePetCustomer(UpdatePetCustomerRequestDTO request) {
        if (requestAccessToken.hasRole("CUST")) {
            Optional<Pet> petOptional = petRepository.findById(request.getId());

            if (petOptional.isEmpty()) {
                throw new InvalidPetException("PET_ID_INVALID");
            }

            customerIdValidator.validateId(request.getCustomerId());
            Pet pet = petOptional.get();
            pet.setCustomer(Customer.builder().id(request.getCustomerId()).build());
            petRepository.save(pet);
        } else {
            throw new UnauthorizedDataAccessException("ACCESS_DENIED");
        }
    }

    @Transactional
    @Override
    public void updatePetDetails(UpdatePetDetailsRequestDTO request) {
        if (requestAccessToken.hasRole("ADMIN")) {
            Optional<Pet> petOptional = petRepository.findById(request.getId());

            if (petOptional.isEmpty()) {
                throw new InvalidPetException("PET_ID_INVALID");
            }

            Pet pet = petOptional.get();
            validateTypeAndNameAndBreed(request, pet);
            pet.setIcon(request.getIcon());
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
        } else {
            throw new UnauthorizedDataAccessException("ACCESS_DENIED");
        }
    }

    private void validateTypeAndNameAndBreed(UpdatePetDetailsRequestDTO request, Pet pet) {
        Pet petWithSameTypeAndNameAndBreed = petRepository.findByTypeAndNameAndBreed(request.getType(), request.getName(), request.getBreed());
        if (petWithSameTypeAndNameAndBreed != null && !petWithSameTypeAndNameAndBreed.getId().equals(pet.getId())) {
            throw new InvalidPetException("OTHER_PET_SAME_TYPE_AND_NAME_AND_BREED");
        }
    }
}
