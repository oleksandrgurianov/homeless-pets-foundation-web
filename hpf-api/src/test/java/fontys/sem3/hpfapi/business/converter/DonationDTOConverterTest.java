package fontys.sem3.hpfapi.business.converter;

import fontys.sem3.hpfapi.dto.customer.CustomerDTO;
import fontys.sem3.hpfapi.dto.donation.DonationDTO;
import fontys.sem3.hpfapi.dto.user.UserDTO;
import fontys.sem3.hpfapi.repository.entity.Customer;
import fontys.sem3.hpfapi.repository.entity.Donation;
import fontys.sem3.hpfapi.repository.entity.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DonationDTOConverterTest {
    @Test
    void shouldConvertAllDonationFieldsToDTO() {
        Donation donationToBeConverted = Donation.builder()
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
                .amount(3.59d)
                .dateOfReceipt(LocalDate.parse("2022-02-13"))
                .description("Dispassionate extraterrestrial observer Vangelis rings of Uranus Flatland the sky calls to us muse about.")
                .build();

        DonationDTO actualDTO = DonationDTOConverter.convertToDTO(donationToBeConverted);

        DonationDTO expectedDTO = DonationDTO.builder()
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
                .amount(3.59d)
                .dateOfReceipt(LocalDate.parse("2022-02-13"))
                .description("Dispassionate extraterrestrial observer Vangelis rings of Uranus Flatland the sky calls to us muse about.")
                .build();

        assertEquals(expectedDTO, actualDTO);
    }
}
