package fontys.sem3.hpfapi.repository;

import fontys.sem3.hpfapi.repository.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {
    List<Donation> findAllByCustomerIdOrderByDateOfReceiptAsc(long customerId);

    List<Donation> findAllByCustomerIdOrderByDateOfReceiptDesc(long customerId);

    List<Donation> findAllByCustomerIdOrderByAmountAsc(long customerId);

    List<Donation> findAllByCustomerIdOrderByAmountDesc(long customerId);

    Donation findById(long id);
}
