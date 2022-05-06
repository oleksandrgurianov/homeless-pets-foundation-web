package fontys.sem3.hpfapi.business;

import fontys.sem3.hpfapi.dto.GetDonationsRequestDTO;
import fontys.sem3.hpfapi.dto.GetDonationsResponseDTO;

public interface GetDonationsUseCase {
    GetDonationsResponseDTO getDonations(GetDonationsRequestDTO request);
}
