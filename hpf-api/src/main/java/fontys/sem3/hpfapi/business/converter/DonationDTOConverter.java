package fontys.sem3.hpfapi.business.converter;

import fontys.sem3.hpfapi.dto.donation.DonationDTO;
import fontys.sem3.hpfapi.repository.entity.Donation;

public class DonationDTOConverter {
    private DonationDTOConverter() {
    }

    public static DonationDTO convertToDTO(Donation donation) {
        return DonationDTO.builder()
                .id(donation.getId())
                .customer(CustomerDTOConverter.convertToDTO(donation.getCustomer()))
                .amount(donation.getAmount())
                .dateOfReceipt(donation.getDateOfReceipt())
                .description(donation.getDescription())
                .build();
    }
}
