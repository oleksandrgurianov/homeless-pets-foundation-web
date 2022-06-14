//package fontys.sem3.hpfapi.business.impl;
//
//import fontys.sem3.hpfapi.dto.CreateDonationRequestDTO;
//import fontys.sem3.hpfapi.dto.CreateDonationResponseDTO;
//import fontys.sem3.hpfapi.repository.DonationRepository;
//import fontys.sem3.hpfapi.repository.entity.Donation;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.LocalDate;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class CreateDonationUseCaseImplTest {
//    @Mock
//    private DonationRepository donationRepositoryMock;
//
//    @InjectMocks
//    private CreateDonationUseCaseImpl createDonationUseCase;
//
//    @Test
//    void shouldSaveNewDonation() {
//        Donation expectedDonationToSave = Donation.builder()
//                .amount(3.59)
//                .dateOfReceipt(LocalDate.now())
//                .description("Dispassionate extraterrestrial observer Vangelis rings of Uranus Flatland the sky calls to us muse about.")
//                .build();
//        Donation savedDonation = Donation.builder()
//                .id(1L)
//                .amount(3.59)
//                .dateOfReceipt(LocalDate.now())
//                .description("Dispassionate extraterrestrial observer Vangelis rings of Uranus Flatland the sky calls to us muse about.")
//                .build();
//        lenient().when(donationRepositoryMock.save(expectedDonationToSave)).thenReturn(savedDonation);
//
//        CreateDonationRequestDTO request = CreateDonationRequestDTO.builder()
//                .amount(3.59)
//                .description("Dispassionate extraterrestrial observer Vangelis rings of Uranus Flatland the sky calls to us muse about.")
//                .build();
//
//        CreateDonationResponseDTO expectedResponse = CreateDonationResponseDTO.builder()
//                .donationId(1L)
//                .build();
//
//        CreateDonationResponseDTO actualResponse = createDonationUseCase.createDonation(request);
//
//        assertEquals(expectedResponse, actualResponse);
//        verify(donationRepositoryMock).existsById(1L);
//        verify(donationRepositoryMock).save(expectedDonationToSave);
//    }
//}
