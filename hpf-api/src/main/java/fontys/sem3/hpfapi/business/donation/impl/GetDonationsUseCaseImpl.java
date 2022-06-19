package fontys.sem3.hpfapi.business.donation.impl;

import fontys.sem3.hpfapi.business.converter.DonationDTOConverter;
import fontys.sem3.hpfapi.business.donation.GetDonationsUseCase;
import fontys.sem3.hpfapi.dto.donation.DonationDTO;
import fontys.sem3.hpfapi.dto.donation.GetDonationsRequestDTO;
import fontys.sem3.hpfapi.dto.donation.GetDonationsResponseDTO;
import fontys.sem3.hpfapi.repository.DonationRepository;
import fontys.sem3.hpfapi.repository.entity.Donation;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetDonationsUseCaseImpl implements GetDonationsUseCase {
    private DonationRepository donationRepository;

    @Override
    public GetDonationsResponseDTO getDonations(GetDonationsRequestDTO request) {
        List<Donation> results;

        if (request.getCustomerId() != null) {
            results = donationRepository.findAllByCustomerIdOrderByDateOfReceiptDesc(request.getCustomerId());
        } else {
            if (request.getStartDate() != null && request.getEndDate() != null) {
                if (BooleanUtils.isNotFalse(request.getOrderByDateOfReceipt())) {
                    if (BooleanUtils.isNotTrue(request.getAscending())) {
                        results = donationRepository.findAllByDateOfReceiptBetweenOrderByDateOfReceiptDesc(request.getStartDate(), request.getEndDate());
                    } else {
                        results = donationRepository.findAllByDateOfReceiptBetweenOrderByDateOfReceiptAsc(request.getStartDate(), request.getEndDate());
                    }
                } else {
                    if (BooleanUtils.isNotFalse(request.getAscending())) {
                        results = donationRepository.findAllByDateOfReceiptBetweenOrderByAmountAsc(request.getStartDate(), request.getEndDate());
                    } else {
                        results = donationRepository.findAllByDateOfReceiptBetweenOrderByAmountDesc(request.getStartDate(), request.getEndDate());
                    }
                }
            } else {
                if (BooleanUtils.isNotTrue(request.getOrderByDateOfReceipt())) {
                    if (BooleanUtils.isNotFalse(request.getAscending())) {
                        results = donationRepository.findAllByOrderByDateOfReceiptAsc();
                    } else {
                        results = donationRepository.findAllByOrderByDateOfReceiptDesc();
                    }
                } else {
                    if (BooleanUtils.isNotFalse(request.getAscending())) {
                        results = donationRepository.findAllByOrderByAmountDesc();
                    } else {
                        results = donationRepository.findAllByOrderByAmountAsc();
                    }
                }
            }
        }

        final GetDonationsResponseDTO response = new GetDonationsResponseDTO();
        List<DonationDTO> donationsDTO = results
                .stream()
                .map(DonationDTOConverter::convertToDTO)
                .toList();
        response.setDonations(donationsDTO);
        return response;
    }
}
