package fontys.sem3.hpfapi.repository.impl;

import fontys.sem3.hpfapi.dto.PetDTO;
import fontys.sem3.hpfapi.repository.PetRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
@Qualifier("pets")
@Primary
public class PetRepositoryImpl implements PetRepository {
    @Override
    public List<PetDTO> getPets() {
        final ArrayList<String> pet1Pictures = new ArrayList<>();
        pet1Pictures.add("https://drive.google.com/file/d/1MoTatuPI6iXDQkaIGfc-sSLqUaCSqsPR/view?usp=sharing");
        pet1Pictures.add("https://drive.google.com/file/d/12GFX_v0jKG9B21Ivd2iEjzkKLffLqKEg/view?usp=sharing");
        pet1Pictures.add("https://drive.google.com/file/d/1C2BK4pZH1ZuC_UdaP9Gudo-WrY4YL6jD/view?usp=sharing");
        pet1Pictures.add("https://drive.google.com/file/d/17QgStDCMqM3Bqnb56H7itR2JmUjiZ0QO/view?usp=sharing");

        final ArrayList<String> pet2Pictures = new ArrayList<>();
        pet2Pictures.add("https://drive.google.com/file/d/18G-f5b2AOrHx5DNUVxWSxXYz1TdCt4y8/view?usp=sharing");
        pet2Pictures.add("https://drive.google.com/file/d/1n11f5xlcymxbUIBv3AcGqVq42jygiy17/view?usp=sharing");
        pet2Pictures.add("https://drive.google.com/file/d/1HSiDMVF4OixBoxxl6-Uh9burDgxS1gHe/view?usp=sharing");

        final ArrayList<String> pet3Pictures = new ArrayList<>();
        pet3Pictures.add("https://drive.google.com/file/d/1XoV_FRuKxOh2yW81sV_vPzcX6TvLhyNC/view?usp=sharing");
        pet3Pictures.add("https://drive.google.com/file/d/1HocLQaq4CbbSyI4UcHM2kW6AJ-TuYEQ1/view?usp=sharing");
        pet3Pictures.add("https://drive.google.com/file/d/1RV4F9E-S1JRLAuL4yQKDKeVdb1O6ZIr8/view?usp=sharing");
        pet3Pictures.add("https://drive.google.com/file/d/1U59l0Xcdyr_UMXpQQzBzSo9rZBSPHFrH/view?usp=sharing");
        pet3Pictures.add("https://drive.google.com/file/d/1RcU6a42uCraa7dFluqFm4fX1zpRtueSQ/view?usp=sharing");
        pet3Pictures.add("https://drive.google.com/file/d/1ceSmsfWQGPiTQ4ne-iGKMLSVIV_8T15L/view?usp=sharing");

        final ArrayList<String> pet4Pictures = new ArrayList<>();
        pet4Pictures.add("https://drive.google.com/file/d/1S2QbBIDdF-Onvq4FvLIq5m0TrPu9GT-e/view?usp=sharing");

        final ArrayList<String> pet5Pictures = new ArrayList<>();
        pet5Pictures.add("https://drive.google.com/file/d/1_ZDhSJzhH5jHkV6oIrSDuhwrkjF_fLPD/view?usp=sharing");
        pet5Pictures.add("https://drive.google.com/file/d/1PxZM4BOWCRyoRpxMt_msLK93zAuSobUD/view?usp=sharing");
        pet5Pictures.add("https://drive.google.com/file/d/1kVP7jv9EJhGjHwkM_xtBiLvkO1DFdtPx/view?usp=sharing");
        pet5Pictures.add("https://drive.google.com/file/d/1j43fOdHh9ndqiMPbJ2dPqAIb2-UWe-_x/view?usp=sharing");
        pet5Pictures.add("https://drive.google.com/file/d/1UGQbZEH9oZf2IRfjKUZcs_p6i_3h9A6C/view?usp=sharing");
        pet5Pictures.add("https://drive.google.com/file/d/1lHYEJfMMH45WQggbVOiaPYS2JtqbLOJZ/view?usp=sharing");

        final ArrayList<String> pet6Pictures = new ArrayList<>();
        pet6Pictures.add("https://drive.google.com/file/d/1F5AGdfgLO5B5Bx6xEJV3O2UffVrtsJG5/view?usp=sharing");
        pet6Pictures.add("https://drive.google.com/file/d/1_6X8gM35cUGCLRuLlcnlcaqIl7hqtWRb/view?usp=sharing");
        pet6Pictures.add("https://drive.google.com/file/d/1d1MrezQpu5rO4Lmxt59GE5WJFgjQaz1o/view?usp=sharing");
        pet6Pictures.add("https://drive.google.com/file/d/1MQKNkwYP8rhYNhgqJzIm8a5aZJwqNeFe/view?usp=sharing");
        pet6Pictures.add("https://drive.google.com/file/d/1tXUm4i011rvLteByc0fShLO4WYbK5_2w/view?usp=sharing");

        final ArrayList<String> pet7Pictures = new ArrayList<>();
        pet7Pictures.add("https://drive.google.com/file/d/1R8dnNlNxa2_R95G21G_ryqDhDMBYRwhC/view?usp=sharing");

        final ArrayList<String> pet8Pictures = new ArrayList<>();
        pet8Pictures.add("https://drive.google.com/file/d/1_Op49ce22_ZcJDkTJZDbZxiANv7RCvSI/view?usp=sharing");
        pet8Pictures.add("https://drive.google.com/file/d/1kLa9LcVwr91YK2pF75-7lSlwMPut8pSf/view?usp=sharing");
        pet8Pictures.add("https://drive.google.com/file/d/1Ck1pUGzkaWheSi5GJdce7jQXXvLt4CW9/view?usp=sharing");

        final ArrayList<String> pet9Pictures = new ArrayList<>();
        pet9Pictures.add("https://drive.google.com/file/d/19xcGpZpJjov08bGx7U4V6SX7-OL4GO8y/view?usp=sharing");
        pet9Pictures.add("https://drive.google.com/file/d/14i5spoJyXg_d5GSZ8ATERmSWCKYp-7Yi/view?usp=sharing");
        pet9Pictures.add("https://drive.google.com/file/d/1C3_dnjDQTXv1ffiVdTE6UEgHb98q9dnh/view?usp=sharing");
        pet9Pictures.add("https://drive.google.com/file/d/1VinuIyibqtq3P4XKRsog0aYazvo6gR5C/view?usp=sharing");
        pet9Pictures.add("https://drive.google.com/file/d/1ZbTLG9mBUpR2SSQ45AHfTyocLtTAAFZf/view?usp=sharing");

        final ArrayList<String> pet10Pictures = new ArrayList<>();
        pet10Pictures.add("https://drive.google.com/file/d/1wG47PtUzERxz4-QwLNxR4oG3Y0INru94/view?usp=sharing");

        final List<PetDTO> petsList = new ArrayList<>();
        petsList.add(new PetDTO(1, pet1Pictures, "DOG", "Bizarre", "Pit Bull Terrier", "Adult", "Female", "Medium", "Black, White/Cream", "Say hello to Bizarre! If you love her name, you will love her personality. It fits her perfectly. Bizarre may take a minute to warm up to you, as she shows her interest in you in a funny way. Once she has met you, she will love you forever. Bizarre still needs a little bit of leash work as she will tug on it when she gets excited, but she learns quickly. When Bizarre is happy, she bounces around and will swing her back end into you. We recommend a home willing to work on training and without young kids as she doesn't realize her size and strength. She is a funny girl that will surely make you laugh. Please stop by and ask to meet her today!", 0));
        petsList.add(new PetDTO(2, pet2Pictures, "DOG", "Jamison", "Terrier Mix", "Young", "Male", "Large", "Brown/Chocolate, White/Cream", "Meet Jamison! He is a 2 year old terrier mix. This handsome guy just loves people and gets the happiest wiggly butt of excitement. Great on the leash and knows basic commands.", 250));
        petsList.add(new PetDTO(3, pet3Pictures, "DOG", "Mini", " Chihuahua", "Senior", "Male", "Small", "Black, White/Cream", "Mini is a sweet older gentleman at 8 years old looking for a quite home to spend his golden years. He enjoys a good belly rub and nap on the couch. He is very loyal to his person and would make someone a wonderful companion. Looking for a mellow guy with lots of love to give? Mini is your man!", 80));
        petsList.add(new PetDTO(4, pet4Pictures, "DOG", "Phillip", "Beagle & Rhodesian Ridgeback Mix", "Puppy", "Male", "Extra Large", null, "Phillip is an active puppy who is coming up with his siblings Will, Carlton, Jazz, Hillary, Ashley and Vivian to find a forever home. He has been very friendly when meeting new adults and very playful with children. Philip gets along with all dogs, but has not been cat tested. He is fine being crated. Philip will thrive in a home where he can be part of all the activities and continue with his training to be his best self.", 0));
        petsList.add(new PetDTO(5, pet5Pictures, "CAT", "Plinko", "Domestic Long Hair", "Adult", "Female", "Large", "Black", "According to her foster mom, Plinko is an easy to love cat. She is very sweet and affectionate and will curl right up in your lap on occasion. She is very playful and follows her foster mom around like a puppy sometimes. Plinko will approach people she has never met before as if she has known them forever, including children. She is very gentle and does get along with other cats. Plinko is looking for a home where she can show her affection to her people, as well as receive it often.", 0));
        petsList.add(new PetDTO(6, pet6Pictures, "CAT", "Gatsby", "Domestic Short Hair", "Young", "Male", "Small", "Black, Gray/Blue/Silver", null, 0));
        petsList.add(new PetDTO(7, pet7Pictures, "CAT", "Dusk", "Tiger", "Kitten", "Male", "Small", null, null, 0));
        petsList.add(new PetDTO(8, pet8Pictures, "RABBIT", "Remy", "Bunny Rabbit Mix", "Young", "Female", "Medium", "Black, White", "Remy enjoys being the princess in her foster home, where she is spoiled and has a nice area to play in. She might enjoy having another bun around as she does not show aggressive behavior with the other rabbits in foster. Remy is slow to warm up to humans. She wants to sit and watch, but she comes around, especially for treats. Miss Remy is looking for a home where she can continue to be the princess of her domain.", 85));
        petsList.add(new PetDTO(9, pet9Pictures, "RAT", "Snowman", "Gerbil", "Adult", "Male", "Small", "White (Dark-Eyed), Blue/Gray", "Snowman (Snow) is a sweetheart! He is looking for a loving family to give him all the attention he deserves. We would love a family with a lone male that would open to bonding him with theirs. Alternatively, we would appreciate a family that would be open to adopting a baby boy to bond with him in the event one comes into the rescue!", 0));
        petsList.add(new PetDTO(10, pet10Pictures, "PARROT", "Lory", "Lorikeet", "Adult", null, "Small", "Blue, Green, Orange", "Hi there! I'm a Lorikeet and I am part of a bonded pair. I require daily \"out time\", lots of socialization, specialized feeding, and I can be VERY noisy. I'm looking for a Lorikeet experienced home that will help me calm down so that my feathers can grown back in. (I plucked them because I was unhappy in my former environment).", 350));
        return petsList;
    }
}
