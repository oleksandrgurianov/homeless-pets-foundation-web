package fontys.sem3.hpfapi.repository;

import fontys.sem3.hpfapi.repository.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    //All

    List<Pet> findAllByTypeAndAdoptedOrderByNameAsc(String type, Boolean adopted);

    List<Pet> findAllByTypeAndAdoptedOrderByNameDesc(String type, Boolean adopted);

    List<Pet> findAllByTypeAndBreedContainingAndAdoptedOrderByNameAsc(String type, String breed, Boolean adopted);

    List<Pet> findAllByTypeAndBreedContainingAndAdoptedOrderByNameDesc(String type, String breed, Boolean adopted);

    Pet findByTypeAndNameAndBreed(String type, String name, String breed);


    //Customer

    List<Pet> findAllByCustomerIdOrderByNameAsc(Long customerId);


    //Administrator

    Boolean existsByTypeAndNameAndBreed(String type, String name, String breed);
}
