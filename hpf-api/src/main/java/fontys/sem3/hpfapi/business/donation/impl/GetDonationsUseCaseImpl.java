package fontys.sem3.hpfapi.business.donation.impl;

import fontys.sem3.hpfapi.business.converter.DonationDTOConverter;
import fontys.sem3.hpfapi.business.donation.GetDonationsUseCase;
import fontys.sem3.hpfapi.business.exception.UnauthorizedDataAccessException;
import fontys.sem3.hpfapi.dto.donation.DonationDTO;
import fontys.sem3.hpfapi.dto.donation.GetDonationsRequestDTO;
import fontys.sem3.hpfapi.dto.donation.GetDonationsResponseDTO;
import fontys.sem3.hpfapi.dto.login.AccessTokenDTO;
import fontys.sem3.hpfapi.repository.DonationRepository;
import fontys.sem3.hpfapi.repository.entity.Donation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetDonationsUseCaseImpl implements GetDonationsUseCase {
    private DonationRepository donationRepository;
    private AccessTokenDTO requestAccessToken;

    @Override
    public GetDonationsResponseDTO getDonations(GetDonationsRequestDTO request) {
        if (requestAccessToken.hasRole("ADMIN")) {
            List<Donation> results;

            if (request.getUserId() != null) {
                results = donationRepository.findAllByCustomerUserIdOrderByDateOfReceiptDesc(request.getUserId());
            } else {
                results = donationRepository.findAllByOrderByDateOfReceiptDesc();
            }

            final GetDonationsResponseDTO response = new GetDonationsResponseDTO();
            List<DonationDTO> donationsDTO = results
                    .stream()
                    .map(DonationDTOConverter::convertToDTO)
                    .toList();
            response.setDonations(donationsDTO);
            return response;
        } else {
            throw new UnauthorizedDataAccessException("ACCESS_DENIED");
        }
    }
}
