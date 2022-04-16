package fontys.sem3.hpfapi.repository;

import fontys.sem3.hpfapi.dto.DonationDTO;

public interface DonationRepository {
    List<DonationDTO> getDonations();
}
