package fontys.sem3.hpfapi.business;

import fontys.sem3.hpfapi.dto.CreateDonationRequestDTO;
import fontys.sem3.hpfapi.dto.CreateDonationResponseDTO;

import javax.transaction.Transactional;

public interface CreateDonationUseCase {
    @Transactional
    CreateDonationResponseDTO createDonation(CreateDonationRequestDTO request);
}
