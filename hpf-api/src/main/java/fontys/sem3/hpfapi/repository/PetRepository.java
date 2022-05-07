package fontys.sem3.hpfapi.repository;

import fontys.sem3.hpfapi.repository.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findAllByTypeAndBreedContainingAndCustomerIdOrderByNameAsc(String type, String breed, Long customerId);

    List<Pet> findAllByTypeAndBreedContainingAndCustomerIdOrderByNameDesc(String type, String breed, Long customerId);

    List<Pet> findAllByTypeAndCustomerIdOrderByNameAsc(String type, Long customerId);

    List<Pet> findAllByTypeAndCustomerIdOrderByNameDesc(String type, Long customerId);

    List<Pet> findAllByBreedContainingAndCustomerIdOrderByNameAsc(String breed, Long customerId);

    List<Pet> findAllByBreedContainingAndCustomerIdOrderByNameDesc(String breed, Long customerId);

    List<Pet> findAllByCustomerIdOrderByNameAsc(Long customerId);

    List<Pet> findAllByCustomerIdOrderByNameDesc(Long customerId);

    Pet findByTypeAndNameAndBreed(String type, String name, String breed);

    Boolean existsByTypeAndNameAndBreed(String type, String name, String breed);
}
