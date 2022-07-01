package fontys.sem3.hpfapi.controller;

import fontys.sem3.hpfapi.business.donation.CreateDonationUseCase;
import fontys.sem3.hpfapi.business.donation.GetDonationsUseCase;
import fontys.sem3.hpfapi.dto.donation.CreateDonationRequestDTO;
import fontys.sem3.hpfapi.dto.donation.CreateDonationResponseDTO;
import fontys.sem3.hpfapi.dto.donation.DonationDTO;
import fontys.sem3.hpfapi.dto.donation.GetDonationsResponseDTO;
import fontys.sem3.hpfapi.repository.entity.Donation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class DonationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateDonationUseCase createDonationUseCase;

    @MockBean
    private GetDonationsUseCase getDonationsUseCase;

    @Test
    void createDonation_shouldReturn201_whenRequestIsValid1() throws Exception {
        CreateDonationRequestDTO expectedRequest = CreateDonationRequestDTO.builder()
                .amount(3.59d)
                .description("Dispassionate extraterrestrial observer Vangelis rings of Uranus Flatland the sky calls to us muse about.")
                .build();
        when(createDonationUseCase.createDonation(expectedRequest))
                .thenReturn(CreateDonationResponseDTO.builder()
                        .donationId(1L)
                        .build());

        mockMvc.perform(post("/donations")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                    "amount": 3.59,
                                    "description": "Dispassionate extraterrestrial observer Vangelis rings of Uranus Flatland the sky calls to us muse about."
                                }
                                """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                        {
                            "donationId": 1
                        }
                        """));

        verify(createDonationUseCase).createDonation(expectedRequest);
    }

    @Test
    void createDonation_shouldReturn201_whenRequestIsValid2() throws Exception {
        CreateDonationRequestDTO expectedRequest = CreateDonationRequestDTO.builder()
                .customerId(1L)
                .amount(3.59d)
                .description("Dispassionate extraterrestrial observer Vangelis rings of Uranus Flatland the sky calls to us muse about.")
                .build();
        when(createDonationUseCase.createDonation(expectedRequest))
                .thenReturn(CreateDonationResponseDTO.builder()
                        .donationId(1L)
                        .build());

        mockMvc.perform(post("/donations")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                    "customerId": "1",
                                    "amount": "3.59",
                                    "description": "Dispassionate extraterrestrial observer Vangelis rings of Uranus Flatland the sky calls to us muse about."
                                }
                                """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                        {
                            "donationId": 1
                        }
                        """));

        verify(createDonationUseCase).createDonation(expectedRequest);
    }

    @Test
    @WithMockUser(username = "admin@hpf.com", roles = {"ADMIN"})
    void getDonations_shouldReturn200WithDonationsList() throws Exception {
        GetDonationsResponseDTO responseDTO = GetDonationsResponseDTO.builder()
                .donations(List.of(
                        DonationDTO.builder()
                                .id(1L)
                                .amount(37.02d)
                                .dateOfReceipt(LocalDate.parse("2022-04-07"))
                                .description("Great turbulent clouds great turbulent clouds the only home we've ever known great turbulent clouds invent the universe great turbulent clouds.")
                                .build(),
                        DonationDTO.builder()
                                .id(2L)
                                .amount(48.19d)
                                .dateOfReceipt(LocalDate.parse("2022-04-09"))
                                .build()
                ))
                .build();
        when(getDonationsUseCase.getDonations()).thenReturn(responseDTO);

        mockMvc.perform(get("/donations"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                        {
                            "donations": [
                                {
                                    "id": 1,
                                    "amount": 37.02,
                                    "dateOfReceipt": "2022-04-07",
                                    "description": "Great turbulent clouds great turbulent clouds the only home we've ever known great turbulent clouds invent the universe great turbulent clouds."
                                },
                                {
                                    "id": 2,
                                    "amount": 48.19,
                                    "dateOfReceipt": "2022-04-09"
                                }
                            ]
                        }
                        """));

        verify(getDonationsUseCase).getDonations();
    }
}
