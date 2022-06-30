package fontys.sem3.hpfapi.business.pet.impl;

import fontys.sem3.hpfapi.business.exception.UnauthorizedDataAccessException;
import fontys.sem3.hpfapi.business.pet.DeletePetUseCase;
import fontys.sem3.hpfapi.dto.login.AccessTokenDTO;
import fontys.sem3.hpfapi.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class DeletePetUseCaseImpl implements DeletePetUseCase {
    private final PetRepository petRepository;
    private final AccessTokenDTO requestAccessToken;

    @Transactional
    @Override
    public void deletePet(long petId) {
        if (requestAccessToken.hasRole("ADMIN")) {
            this.petRepository.deleteById(petId);
        } else {
            throw new UnauthorizedDataAccessException("ACCESS_DENIED");
        }
    }
}
