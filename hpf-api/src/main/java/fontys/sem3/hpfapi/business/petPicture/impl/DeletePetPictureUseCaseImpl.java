package fontys.sem3.hpfapi.business.petPicture.impl;

import fontys.sem3.hpfapi.business.petPicture.DeletePetPictureUseCase;
import fontys.sem3.hpfapi.repository.PetPictureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class DeletePetPictureUseCaseImpl implements DeletePetPictureUseCase {
    private final PetPictureRepository petPictureRepository;

    @Transactional
    @Override
    public void deletePetPicture(long petPictureId) {
        this.petPictureRepository.deleteById(petPictureId);
    }
}
