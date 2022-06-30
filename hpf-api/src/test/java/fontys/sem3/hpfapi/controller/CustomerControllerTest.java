package fontys.sem3.hpfapi.controller;

import fontys.sem3.hpfapi.business.customer.GetCustomerUseCase;
import fontys.sem3.hpfapi.business.customer.UpdateCustomerUseCase;
import fontys.sem3.hpfapi.dto.customer.CustomerDTO;
import fontys.sem3.hpfapi.dto.user.UserDTO;
import fontys.sem3.hpfapi.repository.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetCustomerUseCase getCustomerUseCase;

    @MockBean
    private UpdateCustomerUseCase updateCustomerUseCase;

    @Test
    @WithMockUser(username = "cust1@gmail.com", roles = {"CUST"})
    void getCustomer_shouldReturn404Error_whenCustomerNotFound() throws Exception {
        when(getCustomerUseCase.getCustomer(2L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/customers/2"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(getCustomerUseCase).getCustomer(2L);
    }

    @Test
    @WithMockUser(username = "cust1@gmail.com", roles = {"CUST"})
    void getCustomer_shouldReturn200WithCustomer_whenCustomerFound() throws Exception {
        CustomerDTO customerDTO = CustomerDTO.builder()
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
        when(getCustomerUseCase.getCustomer(2L)).thenReturn(Optional.of(customerDTO));

        mockMvc.perform(get("/customers/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {"id":1,"user":{"id":2,"avatar":"https://drive.google.com/uc?export=view&id=1Rm-dceeyImW5JIBJGBrMiNKUzegRZ_Qo","fullName":"Pascal Broeks","email":"cust1@gmail.com","phoneNumber":"0651535133","password":"password","roles":["CUST"]},"street":"Singel 57","postcode":"3311 HP","city":"Dordrecht","cardNumber":"5561036905645903","expirationDate":"05/26","cvv":"677"}
                        """));

        verify(getCustomerUseCase).getCustomer(2L);
    }
}
