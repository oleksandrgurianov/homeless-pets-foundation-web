package fontys.sem3.hpfapi.business.donation;

import fontys.sem3.hpfapi.dto.donation.CreateDonationRequestDTO;
import fontys.sem3.hpfapi.dto.donation.CreateDonationResponseDTO;

public interface CreateDonationUseCase {
    CreateDonationResponseDTO createDonation(CreateDonationRequestDTO request);
}
