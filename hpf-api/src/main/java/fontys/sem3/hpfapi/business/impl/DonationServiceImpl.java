package fontys.sem3.hpfapi.business.impl;

import fontys.sem3.hpfapi.business.DonationService;
import fontys.sem3.hpfapi.dto.DonationDTO;
import fontys.sem3.hpfapi.repository.DonationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Primary
@Service
@RequiredArgsConstructor
public class DonationServiceImpl implements DonationService {
    private final DonationRepository donationRepository;

    /*
    @Override
    public ArrayList<DonationDTO> getDonationsByCustomerIdSortedByDateOfReceipt(int customerId, boolean ascending) {
        return this.donationRepository.getDonationsByCustomerIdSortedByDateOfReceipt(customerId, ascending);
    }

    @Override
    public ArrayList<DonationDTO> getDonationsByCustomerIdSortedByAmount(int customerId, boolean ascending) {
        return this.donationRepository.getDonationsByCustomerIdSortedByAmount(customerId, ascending);
    }

    @Override
    public DonationDTO getDonationById(int id) {
        return this.donationRepository.getDonationById(id);
    }
    */
}
