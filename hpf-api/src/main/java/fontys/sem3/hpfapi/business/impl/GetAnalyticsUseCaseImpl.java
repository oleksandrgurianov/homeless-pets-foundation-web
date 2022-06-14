package fontys.sem3.hpfapi.business.impl;

import fontys.sem3.hpfapi.business.GetAnalyticsUseCase;
import fontys.sem3.hpfapi.dto.AnalyticsDTO;
import fontys.sem3.hpfapi.repository.DonationRepository;
import fontys.sem3.hpfapi.repository.PetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@AllArgsConstructor
public class GetAnalyticsUseCaseImpl implements GetAnalyticsUseCase {
    private PetRepository petRepository;
    private DonationRepository donationRepository;

    @Override
    public AnalyticsDTO getAnalytics() {
        AnalyticsDTO analyticsDTO = new AnalyticsDTO();
        analyticsDTO.setPetsAdoptedTotal(petRepository.countAllByCustomerIsNotNull());
        analyticsDTO.setCustomersSatisfiedTotal(petRepository.countAllByCustomerDistinct());
        analyticsDTO.setDonationsReceivedTotal(round(donationRepository.sumAll(), 2));
        return analyticsDTO;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
