package fontys.sem3.hpfapi.repository;

import fontys.sem3.hpfapi.repository.entity.Customer;
import fontys.sem3.hpfapi.repository.entity.Donation;
import fontys.sem3.hpfapi.repository.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class DonationRepositoryTest {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private DonationRepository donationRepository;

    @Test
    void sumAll_shouldReturnDonationsSummedByAmount() {
        Donation donation1 = Donation.builder()
                .customer(null)
                .amount(3.59d)
                .dateOfReceipt(LocalDate.parse("2022-02-13"))
                .description("Dispassionate extraterrestrial observer Vangelis rings of Uranus Flatland the sky calls to us muse about.")
                .build();
        entityManager.persist(donation1);

        Donation donation2 = Donation.builder()
                .customer(null)
                .amount(35.41d)
                .dateOfReceipt(LocalDate.parse("2022-02-14"))
                .description("Citizens of distant epochs Euclid of brilliant syntheses stirred by starlight tesseract stirred by starlight.")
                .build();
        entityManager.persist(donation2);

        Double actualSum = donationRepository.sumAll();

        Double expectedSum = donation1.getAmount() + donation2.getAmount();
        assertEquals(actualSum, expectedSum);
    }

    @Test
    void findAllByOrderByDateOfReceiptDesc_shouldReturnAllDonations() {
        Donation donation1 = Donation.builder()
                .customer(null)
                .amount(3.59d)
                .dateOfReceipt(LocalDate.parse("2022-02-13"))
                .description("Dispassionate extraterrestrial observer Vangelis rings of Uranus Flatland the sky calls to us muse about.")
                .build();
        entityManager.persist(donation1);

        Donation donation2 = Donation.builder()
                .customer(null)
                .amount(35.41d)
                .dateOfReceipt(LocalDate.parse("2022-02-14"))
                .description("Citizens of distant epochs Euclid of brilliant syntheses stirred by starlight tesseract stirred by starlight.")
                .build();
        entityManager.persist(donation2);

        List<Donation> actualList = donationRepository.findAllByOrderByDateOfReceiptDesc();

        List<Donation> expectedList = List.of(donation2, donation1);
        assertEquals(expectedList, actualList);
    }
}
