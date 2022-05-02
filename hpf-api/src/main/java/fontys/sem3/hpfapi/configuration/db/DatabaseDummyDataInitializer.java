package fontys.sem3.hpfapi.configuration.db;

import fontys.sem3.hpfapi.repository.*;
import fontys.sem3.hpfapi.repository.entity.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
@AllArgsConstructor
public class DatabaseDummyDataInitializer {
    private AdministratorRepository administratorRepository;
    private CustomerRepository customerRepository;
    private DonationRepository donationRepository;
    private PetPictureRepository petPictureRepository;
    private PetRepository petRepository;
    private UserRepository userRepository;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void populateDatabaseInitialDummyData() {
        if (userRepository.count() == 0) {
            User admin = userRepository.save(
                    User.builder()
                            .avatar("https://drive.google.com/file/d/1_dkDl3VGEjdKqyO900DmdmBGg3DbaVVo/view?usp=sharing")
                            .fullName("Manna Barnhoorn")
                            .email("admin@hpf.com")
                            .phoneNumber("0641261843")
                            .password("password")
                            .role("ADMIN")
                            .build());
            User cust1 = userRepository.save(
                    User.builder()
                            .avatar("https://drive.google.com/file/d/1Rm-dceeyImW5JIBJGBrMiNKUzegRZ_Qo/view?usp=sharing")
                            .fullName("Pascal Broeks")
                            .email("cust1@gmail.com")
                            .phoneNumber("0651535133")
                            .password("password")
                            .role("CUST")
                            .build());
            User cust2 = userRepository.save(
                    User.builder()
                            .avatar(null)
                            .fullName("Ingemar Coumans")
                            .email("cust2@gmail.com")
                            .phoneNumber("0616816346")
                            .password("password")
                            .role("CUST")
                            .build());
            User cust3 = userRepository.save(
                    User.builder()
                            .avatar("https://drive.google.com/file/d/1CCXztuaEGv9H95TfMVBuDyDX3ElL4tBc/view?usp=sharing")
                            .fullName("Pearl Knijnenburg")
                            .email("cust3@gmail.com")
                            .phoneNumber("0682771024")
                            .password("password")
                            .role("CUST")
                            .build());

            administratorRepository.save(
                    Administrator.builder()
                            .user(admin)
                            .jobPosition("Client Service Representative")
                            .build());

            customerRepository.save(
                    Customer.builder()
                            .user(cust1)
                            .street("Singel 57")
                            .postcode("3311 HP")
                            .city("Dordrecht")
                            .cardNumber("5561036905645903")
                            .expirationDate("05/26")
                            .cvv("677")
                            .status(true)
                            .build());
            customerRepository.save(
                    Customer.builder()
                            .user(cust2)
                            .street("Parkweg 132")
                            .postcode("6511 BG")
                            .city("Nijmegen")
                            .cardNumber(null)
                            .expirationDate(null)
                            .cvv(null)
                            .status(true)
                            .build());
            customerRepository.save(
                    Customer.builder()
                            .user(cust3)
                            .street("Andre Severinweg 68")
                            .postcode("6214 PM")
                            .city("Maastricht")
                            .cardNumber("5327789050901654")
                            .expirationDate("05/26")
                            .cvv("170")
                            .status(false)
                            .build());
        }

        if (donationRepository.count() == 0) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                donationRepository.save(
                        Donation.builder()
                                .customer(customerRepository.findById(1L).orElseThrow())
                                .amount(3.59d)
                                .dateOfReceipt(sdf.parse("13/02/2022"))
                                .description("Dispassionate extraterrestrial observer Vangelis rings of Uranus Flatland the sky calls to us muse about.")
                                .build());
                donationRepository.save(
                        Donation.builder()
                                .customer(customerRepository.findById(1L).orElseThrow())
                                .amount(35.41d)
                                .dateOfReceipt(sdf.parse("14/02/2022"))
                                .description("Citizens of distant epochs Euclid of brilliant syntheses stirred by starlight tesseract stirred by starlight.")
                                .build());
                donationRepository.save(
                        Donation.builder()
                                .customer(customerRepository.findById(1L).orElseThrow())
                                .amount(44.32d)
                                .dateOfReceipt(sdf.parse("15/03/2022"))
                                .description(null)
                                .build());
                donationRepository.save(
                        Donation.builder()
                                .customer(customerRepository.findById(2L).orElseThrow())
                                .amount(3.86d)
                                .dateOfReceipt(sdf.parse("18/03/2022"))
                                .description("Concept of the number one stirred by starlight another world something incredible is waiting to be known something incredible is waiting to be known great turbulent clouds?")
                                .build());
                donationRepository.save(
                        Donation.builder()
                                .customer(customerRepository.findById(2L).orElseThrow())
                                .amount(11.21d)
                                .dateOfReceipt(sdf.parse("31/03/2022"))
                                .description(null)
                                .build());
                donationRepository.save(
                        Donation.builder()
                                .customer(customerRepository.findById(3L).orElseThrow())
                                .amount(42.7d)
                                .dateOfReceipt(sdf.parse("01/04/2022"))
                                .description(null)
                                .build());
                donationRepository.save(
                        Donation.builder()
                                .customer(null)
                                .amount(37.02d)
                                .dateOfReceipt(sdf.parse("07/04/2022"))
                                .description("Great turbulent clouds great turbulent clouds the only home we've ever known great turbulent clouds invent the universe great turbulent clouds.")
                                .build());
                donationRepository.save(
                        Donation.builder()
                                .customer(null)
                                .amount(48.19d)
                                .dateOfReceipt(sdf.parse("09/04/2022"))
                                .description(null)
                                .build());
                donationRepository.save(
                        Donation.builder()
                                .customer(null)
                                .amount(34.05d)
                                .dateOfReceipt(sdf.parse("10/04/2022"))
                                .description("Circumnavigated hydrogen atoms with pretty stories for which there's little good evidence of brilliant syntheses white dwarf citizens of distant epochs.")
                                .build());
                donationRepository.save(
                        Donation.builder()
                                .customer(null)
                                .amount(29.14d)
                                .dateOfReceipt(sdf.parse("29/04/2022"))
                                .description(null)
                                .build());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (petRepository.count() == 0) {
            Pet bizarre = petRepository.save(
                    Pet.builder()
                            .customer(customerRepository.findById(1L).orElseThrow())
                            .type("DOG")
                            .name("Bizarre")
                            .breed("Pit Bull Terrier")
                            .ageCategory("Adult")
                            .gender("Female")
                            .size("Medium")
                            .color("Black, White/Cream")
                            .description("Say hello to Bizarre! If you love her name, you will love her personality. It fits her perfectly. Bizarre may take a minute to warm up to you, as she shows her interest in you in a funny way. Once she has met you, she will love you forever. Bizarre still needs a little bit of leash work as she will tug on it when she gets excited, but she learns quickly. When Bizarre is happy, she bounces around and will swing her back end into you. We recommend a home willing to work on training and without young kids as she doesn't realize her size and strength. She is a funny girl that will surely make you laugh. Please stop by and ask to meet her today!")
                            .adoptionFee(0d)
                            .build());
            Pet jamison = petRepository.save(
                    Pet.builder()
                            .customer(null)
                            .type("DOG")
                            .name("Jamison")
                            .breed("Terrier Mix")
                            .ageCategory("Young")
                            .gender("Male")
                            .size("Large")
                            .color("Brown/Chocolate, White/Cream")
                            .description("Meet Jamison! He is a 2 year old terrier mix. This handsome guy just loves people and gets the happiest wiggly butt of excitement. Great on the leash and knows basic commands.")
                            .adoptionFee(250d)
                            .build());
            Pet mini = petRepository.save(
                    Pet.builder()
                            .customer(null)
                            .type("DOG")
                            .name("Mini")
                            .breed("Chihuahua")
                            .ageCategory("Senior")
                            .gender("Male")
                            .size("Small")
                            .color("Black, White/Cream")
                            .description("Mini is a sweet older gentleman at 8 years old looking for a quite home to spend his golden years. He enjoys a good belly rub and nap on the couch. He is very loyal to his person and would make someone a wonderful companion. Looking for a mellow guy with lots of love to give? Mini is your man!")
                            .adoptionFee(80d)
                            .build());
            Pet phillip = petRepository.save(
                    Pet.builder()
                            .customer(null)
                            .type("DOG")
                            .name("Phillip")
                            .breed("Beagle & Rhodesian Ridgeback Mix")
                            .ageCategory("Puppy")
                            .gender("Male")
                            .size("Extra Large")
                            .color(null)
                            .description("Phillip is an active puppy who is coming up with his siblings Will, Carlton, Jazz, Hillary, Ashley and Vivian to find a forever home. He has been very friendly when meeting new adults and very playful with children. Philip gets along with all dogs, but has not been cat tested. He is fine being crated. Philip will thrive in a home where he can be part of all the activities and continue with his training to be his best self.")
                            .adoptionFee(0d)
                            .build());
            Pet plinko = petRepository.save(
                    Pet.builder()
                            .customer(null)
                            .type("CAT")
                            .name("Plinko")
                            .breed("Domestic Long Hair")
                            .ageCategory("Adult")
                            .gender("Female")
                            .size("Large")
                            .color("Black")
                            .description("According to her foster mom, Plinko is an easy to love cat. She is very sweet and affectionate and will curl right up in your lap on occasion. She is very playful and follows her foster mom around like a puppy sometimes. Plinko will approach people she has never met before as if she has known them forever, including children. She is very gentle and does get along with other cats. Plinko is looking for a home where she can show her affection to her people, as well as receive it often.")
                            .adoptionFee(0d)
                            .build());
            Pet gatsby = petRepository.save(
                    Pet.builder()
                            .customer(null)
                            .type("CAT")
                            .name("Gatsby")
                            .breed("Domestic Short Hair")
                            .ageCategory("Young")
                            .gender("Male")
                            .size("Small")
                            .color("Black, Gray/Blue/Silver")
                            .description(null)
                            .adoptionFee(0d)
                            .build());
            Pet dusk = petRepository.save(
                    Pet.builder()
                            .customer(null)
                            .type("CAT")
                            .name("Dusk")
                            .breed("Tiger")
                            .ageCategory("Kitten")
                            .gender("Male")
                            .size("Small")
                            .color(null)
                            .description(null)
                            .adoptionFee(0d)
                            .build());
            Pet remy = petRepository.save(
                    Pet.builder()
                            .customer(null)
                            .type("RABBIT")
                            .name("Remy")
                            .breed("Bunny Rabbit Mix")
                            .ageCategory("Young")
                            .gender("Female")
                            .size("Medium")
                            .color("Black, White")
                            .description("Remy enjoys being the princess in her foster home, where she is spoiled and has a nice area to play in. She might enjoy having another bun around as she does not show aggressive behavior with the other rabbits in foster. Remy is slow to warm up to humans. She wants to sit and watch, but she comes around, especially for treats. Miss Remy is looking for a home where she can continue to be the princess of her domain.")
                            .adoptionFee(85d)
                            .build());
            Pet snowman = petRepository.save(
                    Pet.builder()
                            .customer(null)
                            .type("RAT")
                            .name("Snowman")
                            .breed("Gerbil")
                            .ageCategory("Adult")
                            .gender("Male")
                            .size("Small")
                            .color("White (Dark-Eyed), Blue/Gray")
                            .description("Snowman (Snow) is a sweetheart! He is looking for a loving family to give him all the attention he deserves. We would love a family with a lone male that would open to bonding him with theirs. Alternatively, we would appreciate a family that would be open to adopting a baby boy to bond with him in the event one comes into the rescue!")
                            .adoptionFee(0d)
                            .build());
            Pet lory = petRepository.save(
                    Pet.builder()
                            .customer(null)
                            .type("PARROT")
                            .name("Lory")
                            .breed("Lorikeet")
                            .ageCategory("Adult")
                            .gender(null)
                            .size("Small")
                            .color("Blue, Green, Orange")
                            .description("Hi there! I'm a Lorikeet and I am part of a bonded pair. I require daily \"out time\", lots of socialization, specialized feeding, and I can be VERY noisy. I'm looking for a Lorikeet experienced home that will help me calm down so that my feathers can grown back in. (I plucked them because I was unhappy in my former environment).")
                            .adoptionFee(350d)
                            .build());


            petPictureRepository.save(
                    PetPicture.builder()
                            .pet(bizarre)
                            .picture("https://drive.google.com/file/d/1MoTatuPI6iXDQkaIGfc-sSLqUaCSqsPR/view?usp=sharing")
                            .build());
            petPictureRepository.save(
                    PetPicture.builder()
                            .pet(bizarre)
                            .picture("https://drive.google.com/file/d/12GFX_v0jKG9B21Ivd2iEjzkKLffLqKEg/view?usp=sharing")
                            .build());
            petPictureRepository.save(
                    PetPicture.builder()
                            .pet(bizarre)
                            .picture("https://drive.google.com/file/d/1C2BK4pZH1ZuC_UdaP9Gudo-WrY4YL6jD/view?usp=sharing")
                            .build());
            petPictureRepository.save(
                    PetPicture.builder()
                            .pet(bizarre)
                            .picture("https://drive.google.com/file/d/17QgStDCMqM3Bqnb56H7itR2JmUjiZ0QO/view?usp=sharing")
                            .build());
            petPictureRepository.save(
                    PetPicture.builder()
                            .pet(jamison)
                            .picture("https://drive.google.com/file/d/18G-f5b2AOrHx5DNUVxWSxXYz1TdCt4y8/view?usp=sharing")
                            .build());
            petPictureRepository.save(
                    PetPicture.builder()
                            .pet(jamison)
                            .picture("https://drive.google.com/file/d/1n11f5xlcymxbUIBv3AcGqVq42jygiy17/view?usp=sharing")
                            .build());
            petPictureRepository.save(
                    PetPicture.builder()
                            .pet(jamison)
                            .picture("https://drive.google.com/file/d/1HSiDMVF4OixBoxxl6-Uh9burDgxS1gHe/view?usp=sharing")
                            .build());
            petPictureRepository.save(
                    PetPicture.builder()
                            .pet(mini)
                            .picture("https://drive.google.com/file/d/1XoV_FRuKxOh2yW81sV_vPzcX6TvLhyNC/view?usp=sharing")
                            .build());
            petPictureRepository.save(
                    PetPicture.builder()
                            .pet(mini)
                            .picture("https://drive.google.com/file/d/1HocLQaq4CbbSyI4UcHM2kW6AJ-TuYEQ1/view?usp=sharing")
                            .build());
            petPictureRepository.save(
                    PetPicture.builder()
                            .pet(mini)
                            .picture("https://drive.google.com/file/d/1RV4F9E-S1JRLAuL4yQKDKeVdb1O6ZIr8/view?usp=sharing")
                            .build());
            petPictureRepository.save(
                    PetPicture.builder()
                            .pet(mini)
                            .picture("https://drive.google.com/file/d/1U59l0Xcdyr_UMXpQQzBzSo9rZBSPHFrH/view?usp=sharing")
                            .build());
            petPictureRepository.save(
                    PetPicture.builder()
                            .pet(mini)
                            .picture("https://drive.google.com/file/d/1RcU6a42uCraa7dFluqFm4fX1zpRtueSQ/view?usp=sharing")
                            .build());
            petPictureRepository.save(
                    PetPicture.builder()
                            .pet(mini)
                            .picture("https://drive.google.com/file/d/1ceSmsfWQGPiTQ4ne-iGKMLSVIV_8T15L/view?usp=sharing")
                            .build());
            petPictureRepository.save(
                    PetPicture.builder()
                            .pet(phillip)
                            .picture("https://drive.google.com/file/d/1S2QbBIDdF-Onvq4FvLIq5m0TrPu9GT-e/view?usp=sharing")
                            .build());
            petPictureRepository.save(
                    PetPicture.builder()
                            .pet(plinko)
                            .picture("https://drive.google.com/file/d/1_ZDhSJzhH5jHkV6oIrSDuhwrkjF_fLPD/view?usp=sharing")
                            .build());
            petPictureRepository.save(
                    PetPicture.builder()
                            .pet(plinko)
                            .picture("https://drive.google.com/file/d/1PxZM4BOWCRyoRpxMt_msLK93zAuSobUD/view?usp=sharing")
                            .build());
            petPictureRepository.save(
                    PetPicture.builder()
                            .pet(plinko)
                            .picture("https://drive.google.com/file/d/1kVP7jv9EJhGjHwkM_xtBiLvkO1DFdtPx/view?usp=sharing")
                            .build());
            petPictureRepository.save(
                    PetPicture.builder()
                            .pet(plinko)
                            .picture("https://drive.google.com/file/d/1j43fOdHh9ndqiMPbJ2dPqAIb2-UWe-_x/view?usp=sharing")
                            .build());
            petPictureRepository.save(
                    PetPicture.builder()
                            .pet(plinko)
                            .picture("https://drive.google.com/file/d/1UGQbZEH9oZf2IRfjKUZcs_p6i_3h9A6C/view?usp=sharing")
                            .build());
            petPictureRepository.save(
                    PetPicture.builder()
                            .pet(plinko)
                            .picture("https://drive.google.com/file/d/1lHYEJfMMH45WQggbVOiaPYS2JtqbLOJZ/view?usp=sharing")
                            .build());
            petPictureRepository.save(
                    PetPicture.builder()
                            .pet(gatsby)
                            .picture("https://drive.google.com/file/d/1F5AGdfgLO5B5Bx6xEJV3O2UffVrtsJG5/view?usp=sharing")
                            .build());
            petPictureRepository.save(
                    PetPicture.builder()
                            .pet(gatsby)
                            .picture("https://drive.google.com/file/d/1_6X8gM35cUGCLRuLlcnlcaqIl7hqtWRb/view?usp=sharing")
                            .build());
            petPictureRepository.save(
                    PetPicture.builder()
                            .pet(gatsby)
                            .picture("https://drive.google.com/file/d/1d1MrezQpu5rO4Lmxt59GE5WJFgjQaz1o/view?usp=sharing")
                            .build());
            petPictureRepository.save(
                    PetPicture.builder()
                            .pet(gatsby)
                            .picture("https://drive.google.com/file/d/1MQKNkwYP8rhYNhgqJzIm8a5aZJwqNeFe/view?usp=sharing")
                            .build());
            petPictureRepository.save(
                    PetPicture.builder()
                            .pet(gatsby)
                            .picture("https://drive.google.com/file/d/1tXUm4i011rvLteByc0fShLO4WYbK5_2w/view?usp=sharing")
                            .build());
            petPictureRepository.save(
                    PetPicture.builder()
                            .pet(dusk)
                            .picture("https://drive.google.com/file/d/1R8dnNlNxa2_R95G21G_ryqDhDMBYRwhC/view?usp=sharing")
                            .build());
            petPictureRepository.save(
                    PetPicture.builder()
                            .pet(remy)
                            .picture("https://drive.google.com/file/d/1_Op49ce22_ZcJDkTJZDbZxiANv7RCvSI/view?usp=sharing")
                            .build());
            petPictureRepository.save(
                    PetPicture.builder()
                            .pet(remy)
                            .picture("https://drive.google.com/file/d/1_Op49ce22_ZcJDkTJZDbZxiANv7RCvSI/view?usp=sharing")
                            .build());
            petPictureRepository.save(
                    PetPicture.builder()
                            .pet(remy)
                            .picture("https://drive.google.com/file/d/1_Op49ce22_ZcJDkTJZDbZxiANv7RCvSI/view?usp=sharing")
                            .build());
            petPictureRepository.save(
                    PetPicture.builder()
                            .pet(snowman)
                            .picture("https://drive.google.com/file/d/19xcGpZpJjov08bGx7U4V6SX7-OL4GO8y/view?usp=sharing")
                            .build());
            petPictureRepository.save(
                    PetPicture.builder()
                            .pet(snowman)
                            .picture("https://drive.google.com/file/d/14i5spoJyXg_d5GSZ8ATERmSWCKYp-7Yi/view?usp=sharing")
                            .build());
            petPictureRepository.save(
                    PetPicture.builder()
                            .pet(snowman)
                            .picture("https://drive.google.com/file/d/1C3_dnjDQTXv1ffiVdTE6UEgHb98q9dnh/view?usp=sharing")
                            .build());
            petPictureRepository.save(
                    PetPicture.builder()
                            .pet(snowman)
                            .picture("https://drive.google.com/file/d/1VinuIyibqtq3P4XKRsog0aYazvo6gR5C/view?usp=sharing")
                            .build());
            petPictureRepository.save(
                    PetPicture.builder()
                            .pet(snowman)
                            .picture("https://drive.google.com/file/d/1ZbTLG9mBUpR2SSQ45AHfTyocLtTAAFZf/view?usp=sharing")
                            .build());
            petPictureRepository.save(
                    PetPicture.builder()
                            .pet(lory)
                            .picture("https://drive.google.com/file/d/1wG47PtUzERxz4-QwLNxR4oG3Y0INru94/view?usp=sharing")
                            .build());
        }
    }
}
