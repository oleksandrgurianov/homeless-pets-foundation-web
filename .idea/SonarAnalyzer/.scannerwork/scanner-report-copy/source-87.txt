package fontys.sem3.hpfapi.business;

import fontys.sem3.hpfapi.dto.CreateDonationRequestDTO;
import fontys.sem3.hpfapi.dto.CreateDonationResponseDTO;

public interface CreateDonationUseCase {
    CreateDonationResponseDTO createDonation(CreateDonationRequestDTO request);
}
