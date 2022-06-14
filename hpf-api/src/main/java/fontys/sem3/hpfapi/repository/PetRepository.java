package fontys.sem3.hpfapi.repository;

import fontys.sem3.hpfapi.repository.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    //All

    List<Pet> findAllByCustomerIsNullAndTypeOrderByNameAsc(String type);

    Pet findByTypeAndNameAndBreed(String type, String name, String breed);

    Long countAllByCustomerIsNotNull();

    @Query("SELECT COUNT(DISTINCT customer) FROM Pet")
    Long countAllByCustomerDistinct();


    //Customer

    List<Pet> findAllByCustomerIdOrderByNameAsc(Long customerId);


    //Administrator

    Boolean existsByTypeAndNameAndBreed(String type, String name, String breed);
}
