package fontys.sem3.hpfapi.business.impl;

import fontys.sem3.hpfapi.dto.DonationDTO;
import fontys.sem3.hpfapi.repository.entity.Donation;

final class DonationDTOConverter {
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
