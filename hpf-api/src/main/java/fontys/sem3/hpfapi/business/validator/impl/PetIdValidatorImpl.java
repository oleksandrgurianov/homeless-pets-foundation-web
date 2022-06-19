package fontys.sem3.hpfapi.business.validator.impl;

import fontys.sem3.hpfapi.business.validator.PetIdValidator;
import fontys.sem3.hpfapi.business.exception.InvalidPetException;
import fontys.sem3.hpfapi.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PetIdValidatorImpl implements PetIdValidator {
    private final PetRepository petRepository;

    @Override
    public void validateId(Long petId) {
        if (petId == null || !petRepository.existsById(petId)) {
            throw new InvalidPetException();
        }
    }
}
