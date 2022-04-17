package fontys.sem3.hpfapi.repository.impl;

import fontys.sem3.hpfapi.dto.DonationDTO;
import fontys.sem3.hpfapi.repository.DonationRepository;
import fontys.sem3.hpfapi.repository.TemporaryDatabase;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;

import static java.util.Collections.sort;

@Primary
@Service
public class DonationRepositoryImpl implements DonationRepository {
    private final TemporaryDatabase temporaryDatabase = new TemporaryDatabase();

    @Override
    public ArrayList<DonationDTO> getDonationsSortedByDateOfReceipt(boolean ascending) {
        ArrayList<DonationDTO> donations = this.temporaryDatabase.donationsList;

        Comparator<DonationDTO> compareByDateOfReceipt =
                Comparator.comparing(DonationDTO::getDateOfReceipt);

        if (ascending) {
            sort(donations, compareByDateOfReceipt);
        } else {
            sort(donations, compareByDateOfReceipt.reversed());
        }

        return donations;
    }

    @Override
    public ArrayList<DonationDTO> getDonationsSortedByAmount(boolean ascending) {
        ArrayList<DonationDTO> donations = this.temporaryDatabase.donationsList;

        Comparator<DonationDTO> compareByAmount =
                Comparator.comparing(DonationDTO::getAmount);

        if (ascending) {
            sort(donations, compareByAmount);
        } else {
            sort(donations, compareByAmount.reversed());
        }

        return donations;
    }

    @Override
    public DonationDTO getDonationById(int id) {
        for (DonationDTO d : temporaryDatabase.donationsList) {
            if (d.getId() == id) {
                return d;
            }
        }

        return null;
    }

    @Override
    public ArrayList<DonationDTO> getDonationsByCustomerId(int customerId) {
        ArrayList<DonationDTO> donations = new ArrayList<>();

        for (DonationDTO d : temporaryDatabase.donationsList) {
            if (d.getCustomerId() == customerId) {
                donations.add(d);
            }
        }

        return donations;
    }
}
