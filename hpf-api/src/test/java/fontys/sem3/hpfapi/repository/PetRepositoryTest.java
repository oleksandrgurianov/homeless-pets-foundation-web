package fontys.sem3.hpfapi.repository;

import fontys.sem3.hpfapi.repository.entity.Customer;
import fontys.sem3.hpfapi.repository.entity.Pet;
import fontys.sem3.hpfapi.repository.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class PetRepositoryTest {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PetRepository petRepository;

    @Test
    void findAllByCustomerIsNullAndTypeOrderByNameAsc_shouldReturnPetsFilteredByCustomerAndType() {
        User user = User.builder()
                .avatar("https://drive.google.com/uc?export=view&id=1Rm-dceeyImW5JIBJGBrMiNKUzegRZ_Qo")
                .fullName("Pascal Broeks")
                .email("cust1@gmail.com")
                .phoneNumber("0651535133")
                .password("password")
                .role("CUST")
                .build();
        entityManager.persist(user);

        Customer cust = Customer.builder()
                .user(user)
                .street("Singel 57")
                .postcode("3311 HP")
                .city("Dordrecht")
                .cardNumber("5561036905645903")
                .expirationDate("05/26")
                .cvv("677")
                .build();
        entityManager.persist(cust);

        Pet bizarre = Pet.builder()
                .customer(cust)
                .icon("https://drive.google.com/uc?export=view&id=1MoTatuPI6iXDQkaIGfc-sSLqUaCSqsPR")
                .type("DOGS")
                .name("Bizarre")
                .breed("Pit Bull Terrier")
                .ageCategory("Adult")
                .gender("Female")
                .size("Medium")
                .color("Black, White/Cream")
                .description("Say hello to Bizarre! If you love her name, you will love her personality. It fits her perfectly. Bizarre may take a minute to warm up to you, as she shows her interest in you in a funny way. Once she has met you, she will love you forever. Bizarre still needs a little bit of leash work as she will tug on it when she gets excited, but she learns quickly. When Bizarre is happy, she bounces around and will swing her back end into you. We recommend a home willing to work on training and without young kids as she doesn't realize her size and strength. She is a funny girl that will surely make you laugh. Please stop by and ask to meet her today!")
                .adoptionFee(0d)
                .build();
        entityManager.persist(bizarre);

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

        Pet plinko = Pet.builder()
                .customer(null)
                .icon("https://drive.google.com/uc?export=view&id=1_ZDhSJzhH5jHkV6oIrSDuhwrkjF_fLPD")
                .type("CATS")
                .name("Plinko")
                .breed("Domestic Long Hair")
                .ageCategory("Adult")
                .gender("Female")
                .size("Large")
                .color("Black")
                .description("According to her foster mom, Plinko is an easy to love cat. She is very sweet and affectionate and will curl right up in your lap on occasion. She is very playful and follows her foster mom around like a puppy sometimes. Plinko will approach people she has never met before as if she has known them forever, including children. She is very gentle and does get along with other cats. Plinko is looking for a home where she can show her affection to her people, as well as receive it often.")
                .adoptionFee(0d)
                .build();
        entityManager.persist(plinko);

        List<Pet> actualList = petRepository.findAllByCustomerIsNullAndTypeOrderByNameAsc("DOGS");

        List<Pet> expectedList = List.of(jamison, mini);
        assertEquals(expectedList, actualList);
    }

    @Test
    void findByTypeAndNameAndBreed_shouldReturnPet_whenItExists() {
        entityManager.persist(Pet.builder()
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
                .build());

        Pet actualPet = petRepository.findByTypeAndNameAndBreed("DOGS", "Jamison", "Terrier Mix");

        assertNotNull(actualPet.getId());
        Pet expectedPet = Pet.builder()
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
        assertThat(actualPet)
                .usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .isEqualTo(expectedPet);
    }

    @Test
    void findByTypeAndNameAndBreed_shouldReturnNull_whenPetNotFound() {
        Pet actualPet = petRepository.findByTypeAndNameAndBreed("DOGS", "Jamison", "Terrier Mix");
        assertNull(actualPet);
    }

    @Test
    void countAllByCustomerIsNotNull_shouldReturnPetsCountedAndFilteredByCustomer() {
        User user = User.builder()
                .avatar("https://drive.google.com/uc?export=view&id=1Rm-dceeyImW5JIBJGBrMiNKUzegRZ_Qo")
                .fullName("Pascal Broeks")
                .email("cust1@gmail.com")
                .phoneNumber("0651535133")
                .password("password")
                .role("CUST")
                .build();
        entityManager.persist(user);

        Customer cust = Customer.builder()
                .user(user)
                .street("Singel 57")
                .postcode("3311 HP")
                .city("Dordrecht")
                .cardNumber("5561036905645903")
                .expirationDate("05/26")
                .cvv("677")
                .build();
        entityManager.persist(cust);

        Pet bizarre = Pet.builder()
                .customer(cust)
                .icon("https://drive.google.com/uc?export=view&id=1MoTatuPI6iXDQkaIGfc-sSLqUaCSqsPR")
                .type("DOGS")
                .name("Bizarre")
                .breed("Pit Bull Terrier")
                .ageCategory("Adult")
                .gender("Female")
                .size("Medium")
                .color("Black, White/Cream")
                .description("Say hello to Bizarre! If you love her name, you will love her personality. It fits her perfectly. Bizarre may take a minute to warm up to you, as she shows her interest in you in a funny way. Once she has met you, she will love you forever. Bizarre still needs a little bit of leash work as she will tug on it when she gets excited, but she learns quickly. When Bizarre is happy, she bounces around and will swing her back end into you. We recommend a home willing to work on training and without young kids as she doesn't realize her size and strength. She is a funny girl that will surely make you laugh. Please stop by and ask to meet her today!")
                .adoptionFee(0d)
                .build();
        entityManager.persist(bizarre);

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

        Long actualCount = petRepository.countAllByCustomerIsNotNull();

        Long expectedCount = 1L;
        assertEquals(actualCount, expectedCount);
    }

    @Test
    void countAllByCustomerDistinct_shouldReturnPetsCountedByCustomerDistinct() {
        User user1 = User.builder()
                .avatar("https://drive.google.com/uc?export=view&id=1Rm-dceeyImW5JIBJGBrMiNKUzegRZ_Qo")
                .fullName("Pascal Broeks")
                .email("cust1@gmail.com")
                .phoneNumber("0651535133")
                .password("password")
                .role("CUST")
                .build();
        entityManager.persist(user1);

        Customer cust1 = Customer.builder()
                .user(user1)
                .street("Singel 57")
                .postcode("3311 HP")
                .city("Dordrecht")
                .cardNumber("5561036905645903")
                .expirationDate("05/26")
                .cvv("677")
                .build();
        entityManager.persist(cust1);

        Pet bizarre = Pet.builder()
                .customer(cust1)
                .icon("https://drive.google.com/uc?export=view&id=1MoTatuPI6iXDQkaIGfc-sSLqUaCSqsPR")
                .type("DOGS")
                .name("Bizarre")
                .breed("Pit Bull Terrier")
                .ageCategory("Adult")
                .gender("Female")
                .size("Medium")
                .color("Black, White/Cream")
                .description("Say hello to Bizarre! If you love her name, you will love her personality. It fits her perfectly. Bizarre may take a minute to warm up to you, as she shows her interest in you in a funny way. Once she has met you, she will love you forever. Bizarre still needs a little bit of leash work as she will tug on it when she gets excited, but she learns quickly. When Bizarre is happy, she bounces around and will swing her back end into you. We recommend a home willing to work on training and without young kids as she doesn't realize her size and strength. She is a funny girl that will surely make you laugh. Please stop by and ask to meet her today!")
                .adoptionFee(0d)
                .build();
        entityManager.persist(bizarre);

        Pet jamison = Pet.builder()
                .customer(cust1)
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

        User user2 = User.builder()
                .avatar(null)
                .fullName("Ingemar Coumans")
                .email("cust2@gmail.com")
                .phoneNumber("0616816346")
                .password("password")
                .role("CUST")
                .build();
        entityManager.persist(user2);

        Customer cust2 = Customer.builder()
                .user(user2)
                .street("Parkweg 132")
                .postcode("6511 BG")
                .city("Nijmegen")
                .cardNumber(null)
                .expirationDate(null)
                .cvv(null)
                .build();
        entityManager.persist(cust2);

        Pet mini = Pet.builder()
                .customer(cust2)
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

        Long actualCount = petRepository.countAllByCustomerDistinct();

        Long expectedCount = 2L;
        assertEquals(actualCount, expectedCount);
    }

    @Test
    void findByIdAndCustomerIsNull_shouldReturnPetFilteredByCustomer_whenItExists() {
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

        Optional<Pet> actualPet = petRepository.findByIdAndCustomerIsNull(jamison.getId());

        assertTrue(actualPet.isPresent());
        Optional<Pet> expectedPet = Optional.of(Pet.builder()
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
                .build());
        assertThat(actualPet)
                .usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .isEqualTo(expectedPet);
    }

    @Test
    void findByIdAndCustomerIsNull_shouldReturnNull_whenPetNotFound() {
        Optional<Pet> actualPet = petRepository.findByIdAndCustomerIsNull(101010L);
        assertFalse(actualPet.isPresent());
    }

    @Test
    void findAllByCustomerUserIdOrderByNameAsc_shouldReturnPetsFilteredByCustomerUserId() {
        User user1 = User.builder()
                .avatar("https://drive.google.com/uc?export=view&id=1Rm-dceeyImW5JIBJGBrMiNKUzegRZ_Qo")
                .fullName("Pascal Broeks")
                .email("cust1@gmail.com")
                .phoneNumber("0651535133")
                .password("password")
                .role("CUST")
                .build();
        entityManager.persist(user1);

        Customer cust1 = Customer.builder()
                .user(user1)
                .street("Singel 57")
                .postcode("3311 HP")
                .city("Dordrecht")
                .cardNumber("5561036905645903")
                .expirationDate("05/26")
                .cvv("677")
                .build();
        entityManager.persist(cust1);

        Pet bizarre = Pet.builder()
                .customer(cust1)
                .icon("https://drive.google.com/uc?export=view&id=1MoTatuPI6iXDQkaIGfc-sSLqUaCSqsPR")
                .type("DOGS")
                .name("Bizarre")
                .breed("Pit Bull Terrier")
                .ageCategory("Adult")
                .gender("Female")
                .size("Medium")
                .color("Black, White/Cream")
                .description("Say hello to Bizarre! If you love her name, you will love her personality. It fits her perfectly. Bizarre may take a minute to warm up to you, as she shows her interest in you in a funny way. Once she has met you, she will love you forever. Bizarre still needs a little bit of leash work as she will tug on it when she gets excited, but she learns quickly. When Bizarre is happy, she bounces around and will swing her back end into you. We recommend a home willing to work on training and without young kids as she doesn't realize her size and strength. She is a funny girl that will surely make you laugh. Please stop by and ask to meet her today!")
                .adoptionFee(0d)
                .build();
        entityManager.persist(bizarre);

        Pet jamison = Pet.builder()
                .customer(cust1)
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

        User user2 = User.builder()
                .avatar(null)
                .fullName("Ingemar Coumans")
                .email("cust2@gmail.com")
                .phoneNumber("0616816346")
                .password("password")
                .role("CUST")
                .build();
        entityManager.persist(user2);

        Customer cust2 = Customer.builder()
                .user(user2)
                .street("Parkweg 132")
                .postcode("6511 BG")
                .city("Nijmegen")
                .cardNumber(null)
                .expirationDate(null)
                .cvv(null)
                .build();
        entityManager.persist(cust2);

        Pet mini = Pet.builder()
                .customer(cust2)
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

        List<Pet> actualList = petRepository.findAllByCustomerUserIdOrderByNameAsc(user1.getId());

        List<Pet> expectedList = List.of(bizarre, jamison);
        assertEquals(expectedList, actualList);
    }

    @Test
    void existsByTypeAndNameAndBreed_shouldReturnTrue_whenTypeAndNameAndBreedFound() {
        entityManager.persist(Pet.builder()
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
                .build());

        boolean actual = petRepository.existsByTypeAndNameAndBreed("DOGS", "Jamison", "Terrier Mix");
        assertTrue(actual);
    }

    @Test
    void existsByTypeAndNameAndBreed_shouldReturnFalse_whenTypeAndNameAndBreedNotFound() {
        boolean actual = petRepository.existsByTypeAndNameAndBreed("DOGS", "Jamison", "Terrier Mix");
        assertFalse(actual);
    }
}
