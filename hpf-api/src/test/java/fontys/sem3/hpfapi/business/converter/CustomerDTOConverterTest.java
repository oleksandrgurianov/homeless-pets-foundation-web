package fontys.sem3.hpfapi.business.converter;

import fontys.sem3.hpfapi.dto.customer.CustomerDTO;
import fontys.sem3.hpfapi.dto.user.UserDTO;
import fontys.sem3.hpfapi.repository.entity.Customer;
import fontys.sem3.hpfapi.repository.entity.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerDTOConverterTest {
    @Test
    void shouldConvertAllCustomerFieldsToDTO() {
        Customer customerToBeConverted = Customer.builder()
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
                .build();

        CustomerDTO actualDTO = CustomerDTOConverter.convertToDTO(customerToBeConverted);

        CustomerDTO expectedDTO = CustomerDTO.builder()
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
                .build();

        assertEquals(expectedDTO, actualDTO);
    }
}
