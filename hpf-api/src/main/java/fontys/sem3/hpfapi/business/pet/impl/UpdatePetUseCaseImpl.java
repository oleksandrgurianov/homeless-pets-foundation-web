package fontys.sem3.hpfapi.business.pet.impl;

import fontys.sem3.hpfapi.business.exception.UnauthorizedDataAccessException;
import fontys.sem3.hpfapi.business.validator.CustomerIdValidator;
import fontys.sem3.hpfapi.business.pet.UpdatePetUseCase;
import fontys.sem3.hpfapi.business.exception.InvalidPetException;
import fontys.sem3.hpfapi.dto.login.AccessTokenDTO;
import fontys.sem3.hpfapi.dto.pet.UpdatePetCustomerRequestDTO;
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
}
