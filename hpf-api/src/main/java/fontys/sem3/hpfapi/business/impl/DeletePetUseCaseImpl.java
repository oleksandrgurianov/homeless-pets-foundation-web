package fontys.sem3.hpfapi.business.impl;

import fontys.sem3.hpfapi.business.DeletePetUseCase;
import fontys.sem3.hpfapi.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class DeletePetUseCaseImpl implements DeletePetUseCase {
    private final PetRepository petRepository;

    @Transactional
    @Override
    public void deletePet(long petId) {
        this.petRepository.deleteById(petId);
    }
}
