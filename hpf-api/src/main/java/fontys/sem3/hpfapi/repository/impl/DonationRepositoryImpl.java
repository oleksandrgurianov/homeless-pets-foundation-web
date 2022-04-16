package fontys.sem3.hpfapi.repository.impl;

import fontys.sem3.hpfapi.dto.DonationDTO;
import fontys.sem3.hpfapi.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Qualifier("donations")
@Primary
public class DonationRepositoryImpl {
    @Override
    public List<DonationDTO> getDonations() {
        final List<DonationDTO> donationsList = new ArrayList<>();
        donationsList.add(1, );
        donationsList.add();
        donationsList.add();
        donationsList.add();
        donationsList.add();
        donationsList.add();
        donationsList.add();
        donationsList.add();
        donationsList.add();
        donationsList.add();
    }
}
