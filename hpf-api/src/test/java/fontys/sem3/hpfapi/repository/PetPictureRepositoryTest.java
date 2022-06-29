package fontys.sem3.hpfapi.repository;

import fontys.sem3.hpfapi.repository.entity.Pet;
import fontys.sem3.hpfapi.repository.entity.PetPicture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class PetPictureRepositoryTest {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PetPictureRepository petPictureRepository;

    @Test
    void findAllByPetId_shouldReturnPetPicturesFilteredByPetId() {
        Pet jamison = Pet.builder()
                .customer(null)
                .icon("https://drive.google.com/uc?export=view&id=18G-f5b2AOrHx5DNUVxWSxXYz1TdCt4y8")
                .type("DOGS")
                .name("Jamison")
                .breed("Terrier Mix")
                .ageCategory("Young")
                .gender("Male")
                .size("Large")
                .color("Brown/Chocolate, White/Cream")
                .description("Meet Jamison! He is a 2 year old terrier mix. This handsome guy just loves people and gets the happiest wiggly butt of excitement. Great on the leash and knows basic commands.")
                .adoptionFee(250d)
                .build();
        entityManager.persist(jamison);

        PetPicture petPicture1 = PetPicture.builder()
                .pet(jamison)
                .picture("https://drive.google.com/uc?export=view&id=18G-f5b2AOrHx5DNUVxWSxXYz1TdCt4y8")
                .build();
        entityManager.persist(petPicture1);

        PetPicture petPicture2 = PetPicture.builder()
                .pet(jamison)
                .picture("https://drive.google.com/uc?export=view&id=1n11f5xlcymxbUIBv3AcGqVq42jygiy17")
                .build();
        entityManager.persist(petPicture2);

        Pet mini = Pet.builder()
                .customer(null)
                .icon("https://drive.google.com/uc?export=view&id=1XoV_FRuKxOh2yW81sV_vPzcX6TvLhyNC")
                .type("DOGS")
                .name("Mini")
                .breed("Chihuahua")
                .ageCategory("Senior")
                .gender("Male")
                .size("Small")
                .color("Black, White/Cream")
                .description("Mini is a sweet older gentleman at 8 years old looking for a quite home to spend his golden years. He enjoys a good belly rub and nap on the couch. He is very loyal to his person and would make someone a wonderful companion. Looking for a mellow guy with lots of love to give? Mini is your man!")
                .adoptionFee(80d)
                .build();
        entityManager.persist(mini);

        PetPicture petPicture3 = PetPicture.builder()
                .pet(mini)
                .picture("https://drive.google.com/uc?export=view&id=1XoV_FRuKxOh2yW81sV_vPzcX6TvLhyNC")
                .build();
        entityManager.persist(petPicture3);

        List<PetPicture> actualList = petPictureRepository.findAllByPetId(jamison.getId());

        List<PetPicture> expectedList = List.of(petPicture1, petPicture2);
        assertEquals(expectedList, actualList);
    }
}
