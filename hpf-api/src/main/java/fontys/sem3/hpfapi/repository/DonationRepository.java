package fontys.sem3.hpfapi.repository;

import fontys.sem3.hpfapi.dto.DonationDTO;
import java.util.ArrayList;

public interface DonationRepository {
    ArrayList<DonationDTO> getDonationsSortedByDateOfReceipt(int customerId, boolean ascending);
    ArrayList<DonationDTO> getDonationsSortedByAmount(int customerId, boolean ascending);
    DonationDTO getDonationById(int id);
    ArrayList<DonationDTO> getDonationsByCustomerId(int customerId);
}
