//package fontys.sem3.hpfapi.repository;
//
//import fontys.sem3.hpfapi.repository.entity.Customer;
//import fontys.sem3.hpfapi.repository.entity.Pet;
//import fontys.sem3.hpfapi.repository.entity.User;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import javax.persistence.EntityManager;
//import java.util.List;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@ExtendWith(SpringExtension.class)
//@DataJpaTest
//public class PetRepositoryTest {
//    @Autowired
//    private EntityManager entityManager;
//
//    @Autowired
//    private PetRepository petRepository;
//
//    @Test
//    void findAllByCustomerIdAndTypeOrderByNameAsc_shouldReturnPetsFilteredByCustomerIdAndType() {
//        Customer pascal = saveCustomer("Pascal Broeks", "cust1@gmail.com", "0651535133","password", "CUST", true);
//
//        Pet bizarre = Pet.builder()
//                        .customer(pascal)
//                        .type("DOG")
//                        .name("Bizarre")
//                        .breed("Pit Bull Terrier")
//                        .ageCategory("Adult")
//                        .gender("Female")
//                        .size("Medium")
//                        .color("Black, White/Cream")
//                        .description("Say hello to Bizarre! If you love her name, you will love her personality. It fits her perfectly. Bizarre may take a minute to warm up to you, as she shows her interest in you in a funny way. Once she has met you, she will love you forever. Bizarre still needs a little bit of leash work as she will tug on it when she gets excited, but she learns quickly. When Bizarre is happy, she bounces around and will swing her back end into you. We recommend a home willing to work on training and without young kids as she doesn't realize her size and strength. She is a funny girl that will surely make you laugh. Please stop by and ask to meet her today!")
//                        .adoptionFee(0d)
//                        .build();
//        entityManager.persist(bizarre);
//
//        Pet jamison = Pet.builder()
//                .customer(null)
//                .type("DOG")
//                .name("Jamison")
//                .breed("Terrier Mix")
//                .ageCategory("Young")
//                .gender("Male")
//                .size("Large")
//                .color("Brown/Chocolate, White/Cream")
//                .description("Meet Jamison! He is a 2 year old terrier mix. This handsome guy just loves people and gets the happiest wiggly butt of excitement. Great on the leash and knows basic commands.")
//                .adoptionFee(250d)
//                .build();
//        entityManager.persist(jamison);
//
//        Pet mini = Pet.builder()
//                .customer(null)
//                .type("DOG")
//                .name("Mini")
//                .breed("Chihuahua")
//                .ageCategory("Senior")
//                .gender("Male")
//                .size("Small")
//                .color("Black, White/Cream")
//                .description("Mini is a sweet older gentleman at 8 years old looking for a quite home to spend his golden years. He enjoys a good belly rub and nap on the couch. He is very loyal to his person and would make someone a wonderful companion. Looking for a mellow guy with lots of love to give? Mini is your man!")
//                .adoptionFee(80d)
//                .build();
//        entityManager.persist(mini);
//
//        Pet plinko = Pet.builder()
//                .customer(null)
//                .type("CAT")
//                .name("Plinko")
//                .breed("Domestic Long Hair")
//                .ageCategory("Adult")
//                .gender("Female")
//                .size("Large")
//                .color("Black")
//                .description("According to her foster mom, Plinko is an easy to love cat. She is very sweet and affectionate and will curl right up in your lap on occasion. She is very playful and follows her foster mom around like a puppy sometimes. Plinko will approach people she has never met before as if she has known them forever, including children. She is very gentle and does get along with other cats. Plinko is looking for a home where she can show her affection to her people, as well as receive it often.")
//                .adoptionFee(0d)
//                .build();
//        entityManager.persist(plinko);
//
//        List<Pet> actualList = petRepository.findAllByCustomerIdAndTypeOrderByNameAsc(1L, "DOG");
//
//        List<Pet> expectedList = List.of(bizarre);
//        assertEquals(expectedList, actualList);
//    }
//
//    @Test
//    void findAllByCustomerIdAndTypeOrderByNameAsc_shouldReturnPetsFilteredByType() {
//        Pet jamison = Pet.builder()
//                .customer(null)
//                .type("DOG")
//                .name("Jamison")
//                .breed("Terrier Mix")
//                .ageCategory("Young")
//                .gender("Male")
//                .size("Large")
//                .color("Brown/Chocolate, White/Cream")
//                .description("Meet Jamison! He is a 2 year old terrier mix. This handsome guy just loves people and gets the happiest wiggly butt of excitement. Great on the leash and knows basic commands.")
//                .adoptionFee(250d)
//                .build();
//        entityManager.persist(jamison);
//
//        Pet mini = Pet.builder()
//                .customer(null)
//                .type("DOG")
//                .name("Mini")
//                .breed("Chihuahua")
//                .ageCategory("Senior")
//                .gender("Male")
//                .size("Small")
//                .color("Black, White/Cream")
//                .description("Mini is a sweet older gentleman at 8 years old looking for a quite home to spend his golden years. He enjoys a good belly rub and nap on the couch. He is very loyal to his person and would make someone a wonderful companion. Looking for a mellow guy with lots of love to give? Mini is your man!")
//                .adoptionFee(80d)
//                .build();
//        entityManager.persist(mini);
//
//        Pet plinko = Pet.builder()
//                .customer(null)
//                .type("CAT")
//                .name("Plinko")
//                .breed("Domestic Long Hair")
//                .ageCategory("Adult")
//                .gender("Female")
//                .size("Large")
//                .color("Black")
//                .description("According to her foster mom, Plinko is an easy to love cat. She is very sweet and affectionate and will curl right up in your lap on occasion. She is very playful and follows her foster mom around like a puppy sometimes. Plinko will approach people she has never met before as if she has known them forever, including children. She is very gentle and does get along with other cats. Plinko is looking for a home where she can show her affection to her people, as well as receive it often.")
//                .adoptionFee(0d)
//                .build();
//        entityManager.persist(plinko);
//
//        List<Pet> actualList = petRepository.findAllByCustomerIdAndTypeOrderByNameAsc(null, "DOG");
//
//        List<Pet> expectedList = List.of(jamison, mini);
//        assertEquals(expectedList, actualList);
//    }
//
//    private Customer saveCustomer(String fullName, String email, String phoneNumber, String password, String role, Boolean status) {
//        User user = User.builder()
//                .fullName(fullName)
//                .email(email)
//                .phoneNumber(phoneNumber)
//                .password(password)
//                .role(role)
//                .build();
//        entityManager.persist(user);
//
//        Customer customer = Customer.builder()
//                .user(user)
//                .status(status)
//                .build();
//        entityManager.persist(customer);
//
//        return customer;
//    }
//}
