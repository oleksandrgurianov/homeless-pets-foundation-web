package fontys.sem3.hpfapi.repository;

import fontys.sem3.hpfapi.dto.DonationDTO;
import java.util.List;

public interface DonationRepository {
    List<DonationDTO> getDonations();
}
