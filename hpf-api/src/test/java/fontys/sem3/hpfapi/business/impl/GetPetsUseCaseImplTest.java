//package fontys.sem3.hpfapi.business.impl;
//
//import fontys.sem3.hpfapi.dto.*;
//import fontys.sem3.hpfapi.repository.PetRepository;
//import fontys.sem3.hpfapi.repository.entity.Customer;
//import fontys.sem3.hpfapi.repository.entity.Pet;
//import fontys.sem3.hpfapi.repository.entity.User;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import java.util.List;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class GetPetsUseCaseImplTest {
//    @Mock
//    private PetRepository petRepositoryMock;
//
//    @InjectMocks
//    private GetPetsUseCaseImpl getPetsUseCase;
//
//    @Test
//    void shouldFilterByTypeWhenCustomerIdAndTypeIsInformed() {
//        User cust = User.builder()
//                .id(2L)
//                .avatar("https://drive.google.com/file/d/1Rm-dceeyImW5JIBJGBrMiNKUzegRZ_Qo/view?usp=sharing")
//                .fullName("Pascal Broeks")
//                .email("cust1@gmail.com")
//                .phoneNumber("0651535133")
//                .password("password")
//                .role("CUST")
//                .build();
//        Customer pascal = Customer.builder()
//                .id(1L)
//                .user(cust)
//                .street("Singel 57")
//                .postcode("3311 HP")
//                .city("Dordrecht")
//                .cardNumber("5561036905645903")
//                .expirationDate("05/26")
//                .cvv("677")
//                .status(true)
//                .build();
//        when(petRepositoryMock.findAllByCustomerIdAndTypeOrderByNameAsc(1L, "DOG"))
//                .thenReturn(List.of(
//                        Pet.builder()
//                                .customer(pascal)
//                                .type("DOG")
//                                .name("Bizarre")
//                                .breed("Pit Bull Terrier")
//                                .ageCategory("Adult")
//                                .gender("Female")
//                                .size("Medium")
//                                .color("Black, White/Cream")
//                                .description("Say hello to Bizarre! If you love her name, you will love her personality. It fits her perfectly. Bizarre may take a minute to warm up to you, as she shows her interest in you in a funny way. Once she has met you, she will love you forever. Bizarre still needs a little bit of leash work as she will tug on it when she gets excited, but she learns quickly. When Bizarre is happy, she bounces around and will swing her back end into you. We recommend a home willing to work on training and without young kids as she doesn't realize her size and strength. She is a funny girl that will surely make you laugh. Please stop by and ask to meet her today!")
//                                .adoptionFee(0d)
//                                .build()
//                ));
//
//        GetPetsResponseDTO actualResponse = getPetsUseCase.getPets(GetPetsRequestDTO.builder()
//                .customerId(1L)
//                .type("DOG")
//                .build());
//
//        UserDTO custDTO = UserDTO.builder()
//                .id(2L)
//                .avatar("https://drive.google.com/file/d/1Rm-dceeyImW5JIBJGBrMiNKUzegRZ_Qo/view?usp=sharing")
//                .fullName("Pascal Broeks")
//                .email("cust1@gmail.com")
//                .phoneNumber("0651535133")
//                .password("password")
//                .role("CUST")
//                .build();
//        CustomerDTO pascalDTO = CustomerDTO.builder()
//                .id(1L)
//                .user(custDTO)
//                .street("Singel 57")
//                .postcode("3311 HP")
//                .city("Dordrecht")
//                .cardNumber("5561036905645903")
//                .expirationDate("05/26")
//                .cvv("677")
//                .status(true)
//                .build();
//        GetPetsResponseDTO expectedResponse = GetPetsResponseDTO.builder()
//                .pets(List.of(
//                        PetDTO.builder()
//                                .customer(pascalDTO)
//                                .type("DOG")
//                                .name("Bizarre")
//                                .breed("Pit Bull Terrier")
//                                .ageCategory("Adult")
//                                .gender("Female")
//                                .size("Medium")
//                                .color("Black, White/Cream")
//                                .description("Say hello to Bizarre! If you love her name, you will love her personality. It fits her perfectly. Bizarre may take a minute to warm up to you, as she shows her interest in you in a funny way. Once she has met you, she will love you forever. Bizarre still needs a little bit of leash work as she will tug on it when she gets excited, but she learns quickly. When Bizarre is happy, she bounces around and will swing her back end into you. We recommend a home willing to work on training and without young kids as she doesn't realize her size and strength. She is a funny girl that will surely make you laugh. Please stop by and ask to meet her today!")
//                                .adoptionFee(0d)
//                                .build()
//                ))
//                .build();
//
//        assertEquals(expectedResponse, actualResponse);
//        verify(petRepositoryMock).findAllByCustomerIdAndTypeOrderByNameAsc(1L, "DOG");
//    }
//}
