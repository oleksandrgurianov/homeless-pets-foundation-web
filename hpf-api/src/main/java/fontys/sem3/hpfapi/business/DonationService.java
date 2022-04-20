package fontys.sem3.hpfapi.business;

import fontys.sem3.hpfapi.dto.DonationDTO;

import java.util.ArrayList;

public interface DonationService {
    ArrayList<DonationDTO> getDonationsByCustomerIdSortedByDateOfReceipt(int customerId, boolean ascending);

    ArrayList<DonationDTO> getDonationsByCustomerIdSortedByAmount(int customerId, boolean ascending);

    DonationDTO getDonationById(int id);
}
