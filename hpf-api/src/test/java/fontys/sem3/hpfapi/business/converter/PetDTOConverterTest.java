package fontys.sem3.hpfapi.business.converter;

import fontys.sem3.hpfapi.dto.customer.CustomerDTO;
import fontys.sem3.hpfapi.dto.pet.PetDTO;
import fontys.sem3.hpfapi.dto.user.UserDTO;
import fontys.sem3.hpfapi.repository.entity.Customer;
import fontys.sem3.hpfapi.repository.entity.Pet;
import fontys.sem3.hpfapi.repository.entity.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PetDTOConverterTest {
    @Test
    void shouldConvertAllPetFieldsToDTO() {
        Pet petToBeConverted = Pet.builder()
                .id(1L)
                .customer(Customer.builder()
                        .id(1L)
                        .user(User.builder()
                                .id(2L)
                                .avatar("https://drive.google.com/uc?export=view&id=1Rm-dceeyImW5JIBJGBrMiNKUzegRZ_Qo")
                                .fullName("Pascal Broeks")
                                .email("cust1@gmail.com")
                                .phoneNumber("0651535133")
                                .password("password")
                                .role("CUST")
                                .build())
                        .street("Singel 57")
                        .postcode("3311 HP")
                        .city("Dordrecht")
                        .cardNumber("5561036905645903")
                        .expirationDate("05/26")
                        .cvv("677")
                        .build())
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

        PetDTO actualDTO = PetDTOConverter.convertToDTO(petToBeConverted);

        PetDTO expectedDTO = PetDTO.builder()
                .id(1L)
                .customer(CustomerDTO.builder()
                        .id(1L)
                        .user(UserDTO.builder()
                                .id(2L)
                                .avatar("https://drive.google.com/uc?export=view&id=1Rm-dceeyImW5JIBJGBrMiNKUzegRZ_Qo")
                                .fullName("Pascal Broeks")
                                .email("cust1@gmail.com")
                                .phoneNumber("0651535133")
                                .password("password")
                                .roles(List.of("CUST"))
                                .build())
                        .street("Singel 57")
                        .postcode("3311 HP")
                        .city("Dordrecht")
                        .cardNumber("5561036905645903")
                        .expirationDate("05/26")
                        .cvv("677")
                        .build())
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

        assertEquals(expectedDTO, actualDTO);
    }
}
