package fontys.sem3.hpfapi.business.donation;

import fontys.sem3.hpfapi.business.donation.impl.GetDonationsUseCaseImpl;
import fontys.sem3.hpfapi.business.exception.UnauthorizedDataAccessException;
import fontys.sem3.hpfapi.dto.donation.DonationDTO;
import fontys.sem3.hpfapi.dto.donation.GetDonationsResponseDTO;
import fontys.sem3.hpfapi.dto.login.AccessTokenDTO;
import fontys.sem3.hpfapi.repository.DonationRepository;
import fontys.sem3.hpfapi.repository.entity.Donation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetDonationsUseCaseImplTest {
    @Mock
    private DonationRepository donationRepositoryMock;

    @Mock
    private AccessTokenDTO requestAccessToken;

    @InjectMocks
    private GetDonationsUseCaseImpl getDonationsUseCase;

    @Test
    void shouldThrowUnauthorizedDataAccessExceptionWhenUserRoleNotMatched() {
        when(requestAccessToken.hasRole("ADMIN"))
                .thenReturn(false);

        UnauthorizedDataAccessException unauthorizedDataAccessException = assertThrows(UnauthorizedDataAccessException.class,
                () -> getDonationsUseCase.getDonations());

        assertEquals("ACCESS_DENIED", unauthorizedDataAccessException.getReason());

        verify(requestAccessToken).hasRole("ADMIN");
    }

    @Test
    void shouldReturnAllDonations() {
        when(donationRepositoryMock.findAllByOrderByDateOfReceiptDesc())
                .thenReturn(List.of(
                        Donation.builder()
                                .id(1L)
                                .customer(null)
                                .amount(37.02d)
                                .dateOfReceipt(LocalDate.parse("2022-04-07"))
                                .description("Great turbulent clouds great turbulent clouds the only home we've ever known great turbulent clouds invent the universe great turbulent clouds.")
                                .build(),
                        Donation.builder()
                                .id(2L)
                                .customer(null)
                                .amount(48.19d)
                                .dateOfReceipt(LocalDate.parse("2022-04-09"))
                                .description(null)
                                .build()
                ));

        when(requestAccessToken.hasRole("ADMIN"))
                .thenReturn(true);

        GetDonationsResponseDTO actualResponse = getDonationsUseCase.getDonations();

        GetDonationsResponseDTO expectedResponse = GetDonationsResponseDTO.builder()
                .donations(List.of(
                        DonationDTO.builder()
                                .id(1L)
                                .customer(null)
                                .amount(37.02d)
                                .dateOfReceipt(LocalDate.parse("2022-04-07"))
                                .description("Great turbulent clouds great turbulent clouds the only home we've ever known great turbulent clouds invent the universe great turbulent clouds.")
                                .build(),
                        DonationDTO.builder()
                                .id(2L)
                                .customer(null)
                                .amount(48.19d)
                                .dateOfReceipt(LocalDate.parse("2022-04-09"))
                                .description(null)
                                .build()
                ))
                .build();
        assertEquals(expectedResponse, actualResponse);

        verify(donationRepositoryMock).findAllByOrderByDateOfReceiptDesc();
        verify(requestAccessToken).hasRole("ADMIN");
    }
}
