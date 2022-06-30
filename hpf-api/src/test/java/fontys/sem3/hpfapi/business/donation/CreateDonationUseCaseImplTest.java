package fontys.sem3.hpfapi.business.donation;

import fontys.sem3.hpfapi.business.donation.impl.CreateDonationUseCaseImpl;
import fontys.sem3.hpfapi.business.exception.InvalidCustomerException;
import fontys.sem3.hpfapi.business.validator.CustomerIdValidator;
import fontys.sem3.hpfapi.dto.donation.CreateDonationRequestDTO;
import fontys.sem3.hpfapi.dto.donation.CreateDonationResponseDTO;
import fontys.sem3.hpfapi.repository.DonationRepository;
import fontys.sem3.hpfapi.repository.entity.Customer;
import fontys.sem3.hpfapi.repository.entity.Donation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateDonationUseCaseImplTest {
    @Mock
    private DonationRepository donationRepositoryMock;

    @Mock
    private CustomerIdValidator customerIdValidatorMock;

    @InjectMocks
    private CreateDonationUseCaseImpl createDonationUseCase;

    @Test
    void shouldSaveNewDonation() {
        Donation expectedDonationToSave = Donation.builder()
                .customer(null)
                .amount(37.02d)
                .dateOfReceipt(LocalDate.now())
                .description("Great turbulent clouds great turbulent clouds the only home we've ever known great turbulent clouds invent the universe great turbulent clouds.")
                .build();
        Donation savedDonation = Donation.builder()
                .id(1L)
                .customer(null)
                .amount(37.02d)
                .dateOfReceipt(LocalDate.now())
                .description("Great turbulent clouds great turbulent clouds the only home we've ever known great turbulent clouds invent the universe great turbulent clouds.")
                .build();
        when(donationRepositoryMock.save(expectedDonationToSave)).thenReturn(savedDonation);

        CreateDonationRequestDTO request = CreateDonationRequestDTO.builder()
                .customerId(null)
                .amount(37.02d)
                .description("Great turbulent clouds great turbulent clouds the only home we've ever known great turbulent clouds invent the universe great turbulent clouds.")
                .build();

        CreateDonationResponseDTO actualResponse = createDonationUseCase.createDonation(request);

        CreateDonationResponseDTO expectedResponse = CreateDonationResponseDTO.builder()
                .donationId(1L)
                .build();
        assertEquals(expectedResponse, actualResponse);
        verify(donationRepositoryMock).save(expectedDonationToSave);
    }

    @Test
    void shouldThrowInvalidCustomerExceptionWhenCustomerIdInvalid() {
        doThrow(InvalidCustomerException.class)
                .when(customerIdValidatorMock).validateId(1L);

        CreateDonationRequestDTO request = CreateDonationRequestDTO.builder()
                .customerId(1L)
                .amount(37.02d)
                .description("Great turbulent clouds great turbulent clouds the only home we've ever known great turbulent clouds invent the universe great turbulent clouds.")
                .build();
        assertThrows(InvalidCustomerException.class, () -> createDonationUseCase.createDonation(request));

        verify(customerIdValidatorMock).validateId(1L);
    }

    @Test
    void shouldSaveNewDonationWithCustomerIdAssigned() {
        Donation expectedDonationToSave = Donation.builder()
                .customer(Customer.builder().id(1L).build())
                .amount(37.02d)
                .dateOfReceipt(LocalDate.now())
                .description("Great turbulent clouds great turbulent clouds the only home we've ever known great turbulent clouds invent the universe great turbulent clouds.")
                .build();
        Donation savedDonation = Donation.builder()
                .id(1L)
                .customer(Customer.builder().id(1L).build())
                .amount(37.02d)
                .dateOfReceipt(LocalDate.now())
                .description("Great turbulent clouds great turbulent clouds the only home we've ever known great turbulent clouds invent the universe great turbulent clouds.")
                .build();
        when(donationRepositoryMock.save(expectedDonationToSave)).thenReturn(savedDonation);

        CreateDonationRequestDTO request = CreateDonationRequestDTO.builder()
                .customerId(1L)
                .amount(37.02d)
                .description("Great turbulent clouds great turbulent clouds the only home we've ever known great turbulent clouds invent the universe great turbulent clouds.")
                .build();

        CreateDonationResponseDTO actualResponse = createDonationUseCase.createDonation(request);

        CreateDonationResponseDTO expectedResponse = CreateDonationResponseDTO.builder()
                .donationId(1L)
                .build();
        assertEquals(expectedResponse, actualResponse);
        verify(customerIdValidatorMock).validateId(1L);
        verify(donationRepositoryMock).save(expectedDonationToSave);
    }
}
