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
    void findAllByCustomerUserIdOrderByDateOfReceiptDesc_shouldReturnDonationsFilteredByCustomerUserId() {
        User user1 = User.builder()
                .avatar("https://drive.google.com/uc?export=view&id=1Rm-dceeyImW5JIBJGBrMiNKUzegRZ_Qo")
                .fullName("Pascal Broeks")
                .email("cust1@gmail.com")
                .phoneNumber("0651535133")
                .password("password")
                .role("CUST")
                .build();
        entityManager.persist(user1);

        Customer cust1 = Customer.builder()
                .user(user1)
                .street("Singel 57")
                .postcode("3311 HP")
                .city("Dordrecht")
                .cardNumber("5561036905645903")
                .expirationDate("05/26")
                .cvv("677")
                .build();
        entityManager.persist(cust1);

        Donation donation1 = Donation.builder()
                .customer(cust1)
                .amount(3.59d)
                .dateOfReceipt(LocalDate.parse("2022-02-13"))
                .description("Dispassionate extraterrestrial observer Vangelis rings of Uranus Flatland the sky calls to us muse about.")
                .build();
        entityManager.persist(donation1);

        Donation donation2 = Donation.builder()
                .customer(cust1)
                .amount(35.41d)
                .dateOfReceipt(LocalDate.parse("2022-02-14"))
                .description("Citizens of distant epochs Euclid of brilliant syntheses stirred by starlight tesseract stirred by starlight.")
                .build();
        entityManager.persist(donation2);

        User user2 = User.builder()
                .avatar(null)
                .fullName("Ingemar Coumans")
                .email("cust2@gmail.com")
                .phoneNumber("0616816346")
                .password("password")
                .role("CUST")
                .build();
        entityManager.persist(user2);

        Customer cust2 = Customer.builder()
                .user(user2)
                .street("Parkweg 132")
                .postcode("6511 BG")
                .city("Nijmegen")
                .cardNumber(null)
                .expirationDate(null)
                .cvv(null)
                .build();
        entityManager.persist(cust2);

        Donation donation3 = Donation.builder()
                .customer(cust2)
                .amount(35.41d)
                .dateOfReceipt(LocalDate.parse("2022-02-14"))
                .description("Citizens of distant epochs Euclid of brilliant syntheses stirred by starlight tesseract stirred by starlight.")
                .build();
        entityManager.persist(donation3);

        List<Donation> actualList = donationRepository.findAllByCustomerUserIdOrderByDateOfReceiptDesc(user1.getId());

        List<Donation> expectedList = List.of(donation2, donation1);
        assertEquals(expectedList, actualList);
    }

    @Test
    void findAllByCustomerUserIdOrderByDateOfReceiptDesc_shouldReturnAllDonationsWhenNoCustomerUserIdInformed() {
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

        List<Donation> actualList = donationRepository.findAllByCustomerUserIdOrderByDateOfReceiptDesc(null);

        List<Donation> expectedList = List.of(donation2, donation1);
        assertEquals(expectedList, actualList);
    }
}
