package fontys.sem3.hpfapi.repository;

import fontys.sem3.hpfapi.repository.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findAllByBreedContainingAndCustomerIdOrderByNameAsc(String breed, Long customerId);

    List<Pet> findAllByBreedContainingAndCustomerIdOrderByNameDesc(String breed, Long customerId);

    List<Pet> findAllByCustomerIdOrderByNameAsc(Long customerId);

    Pet findByTypeAndNameAndBreed(String type, String name, String breed);

    Boolean existsByTypeAndNameAndBreed(String type, String name, String breed);
}
