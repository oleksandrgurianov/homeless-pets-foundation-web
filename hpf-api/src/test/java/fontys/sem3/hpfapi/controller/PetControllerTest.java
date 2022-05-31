//package fontys.sem3.hpfapi.controller;
//
//import fontys.sem3.hpfapi.business.CreatePetUseCase;
//import fontys.sem3.hpfapi.business.GetPetsUseCase;
//import fontys.sem3.hpfapi.dto.*;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import java.util.List;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
//
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(PetController.class)
//public class PetControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private GetPetsUseCase getPetsUseCase;
//
//    @MockBean
//    private CreatePetUseCase createPetUseCase;
//
//    @Test
//    void getPets_shouldReturn200WithPetsList_WhenNoFilterProvided() throws Exception {
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
//
//        GetPetsResponseDTO response = GetPetsResponseDTO.builder()
//                .pets(List.of(
//                        PetDTO.builder()
//                                .id(1L)
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
//                                .build(),
//                        PetDTO.builder()
//                                .id(1L)
//                                .customer(null)
//                                .type("DOG")
//                                .name("Jamison")
//                                .breed("Terrier Mix")
//                                .ageCategory("Young")
//                                .gender("Male")
//                                .size("Large")
//                                .color("Brown/Chocolate, White/Cream")
//                                .description("Meet Jamison! He is a 2 year old terrier mix. This handsome guy just loves people and gets the happiest wiggly butt of excitement. Great on the leash and knows basic commands.")
//                                .adoptionFee(250d)
//                                .build()
//                ))
//                .build();
//        GetPetsRequestDTO request = GetPetsRequestDTO.builder().build();
//        when(getPetsUseCase.getPets(request)).thenReturn(response);
//
//        mockMvc.perform(get("/pets/DOG"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
//                .andExpect(content().json("""
//                            {
//                                "pets":[
//                                    {"id":1,"pcn":111,"name":"Romario","country":{"id":1,"code":"BR","name":"Brazil"}},
//                                    {"id":1,"pcn":222,"name":"Ronaldo","country":{"id":1,"code":"BR","name":"Brazil"}}
//                                ]
//                            }
//                        """));
//
//        verify(getPetsUseCase).getPets(request);
//    }
//}
