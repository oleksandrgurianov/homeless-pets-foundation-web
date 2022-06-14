package fontys.sem3.hpfapi.business.impl;

import fontys.sem3.hpfapi.business.CreateDonationUseCase;
import fontys.sem3.hpfapi.business.CustomerIdValidator;
import fontys.sem3.hpfapi.dto.CreateDonationRequestDTO;
import fontys.sem3.hpfapi.dto.CreateDonationResponseDTO;
import fontys.sem3.hpfapi.repository.DonationRepository;
import fontys.sem3.hpfapi.repository.entity.Customer;
import fontys.sem3.hpfapi.repository.entity.Donation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CreateDonationUseCaseImpl implements CreateDonationUseCase {
    private final DonationRepository donationRepository;
    private final CustomerIdValidator customerIdValidator;

    @Transactional
    @Override
    public CreateDonationResponseDTO createDonation(CreateDonationRequestDTO request) {
        Donation newDonation;

        if (request.getCustomerId() != null) {
            customerIdValidator.validateId(request.getCustomerId());

            newDonation = Donation.builder()
                    .customer(Customer.builder().id(request.getCustomerId()).build())
                    .amount(request.getAmount())
                    .dateOfReceipt(LocalDate.now())
                    .description(request.getDescription())
                    .build();
        } else {
            newDonation = Donation.builder()
                    .amount(request.getAmount())
                    .dateOfReceipt(LocalDate.now())
                    .description(request.getDescription())
                    .build();
        }

        Donation savedDonation = donationRepository.save(newDonation);

        return CreateDonationResponseDTO.builder()
                .donationId(savedDonation.getId())
                .build();
    }
}
