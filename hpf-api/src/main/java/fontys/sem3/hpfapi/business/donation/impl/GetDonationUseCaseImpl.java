package fontys.sem3.hpfapi.business.donation.impl;

import fontys.sem3.hpfapi.business.converter.DonationDTOConverter;
import fontys.sem3.hpfapi.business.donation.GetDonationUseCase;
import fontys.sem3.hpfapi.dto.donation.DonationDTO;
import fontys.sem3.hpfapi.repository.DonationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetDonationUseCaseImpl implements GetDonationUseCase {
    private DonationRepository donationRepository;

    @Override
    public Optional<DonationDTO> getDonation(long donationId) {
        return donationRepository.findById(donationId).map(DonationDTOConverter::convertToDTO);
    }
}
