package fontys.sem3.hpfapi.repository;

import fontys.sem3.hpfapi.repository.entity.PetPicture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetPictureRepository extends JpaRepository<PetPicture, Long> {
    //All

    List<PetPicture> findAllByPetId(long petId);
}
