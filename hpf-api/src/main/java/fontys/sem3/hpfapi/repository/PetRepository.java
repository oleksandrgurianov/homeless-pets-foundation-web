package fontys.sem3.hpfapi.repository;

import fontys.sem3.hpfapi.repository.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    //All

    List<Pet> findAllByCustomerIsNullAndTypeOrderByNameAsc(String type);

    List<Pet> findAllByCustomerIsNullAndTypeOrderByNameDesc(String type);

    List<Pet> findAllByCustomerIsNullAndTypeOrderByAdoptionFeeAsc(String type);

    List<Pet> findAllByCustomerIsNullAndTypeOrderByAdoptionFeeDesc(String type);

    List<Pet> findAllByCustomerIsNullAndTypeAndBreedContainingOrderByNameAsc(String type, String breed);

    List<Pet> findAllByCustomerIsNullAndTypeAndBreedContainingOrderByNameDesc(String type, String breed);

    List<Pet> findAllByCustomerIsNullAndTypeAndBreedContainingOrderByAdoptionFeeAsc(String type, String breed);

    List<Pet> findAllByCustomerIsNullAndTypeAndBreedContainingOrderByAdoptionFeeDesc(String type, String breed);

    Pet findByTypeAndNameAndBreed(String type, String name, String breed);

    Long countAllByCustomerIsNotNull();

    @Query("SELECT COUNT(DISTINCT customer) FROM Pet")
    Long countAllByCustomerDistinct();


    //Customer

    List<Pet> findAllByCustomerIdOrderByNameAsc(Long customerId);


    //Administrator

    Boolean existsByTypeAndNameAndBreed(String type, String name, String breed);
}
