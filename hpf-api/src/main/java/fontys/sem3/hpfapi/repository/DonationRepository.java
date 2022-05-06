package fontys.sem3.hpfapi.repository;

import fontys.sem3.hpfapi.repository.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;
import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {
    List<Donation> findAllByDateOfReceiptBetweenOrderByDateOfReceiptAsc(Date startDate, Date endDate);

    List<Donation> findAllByDateOfReceiptBetweenOrderByDateOfReceiptDesc(Date startDate, Date endDate);

    List<Donation> findAllByDateOfReceiptBetweenOrderByAmountAsc(Date startDate, Date endDate);

    List<Donation> findAllByDateOfReceiptBetweenOrderByAmountDesc(Date startDate, Date endDate);

    List<Donation> findAllOrderByDateOfReceiptAsc();

    List<Donation> findAllOrderByDateOfReceiptDesc();

    List<Donation> findAllOrderByAmountAsc();

    List<Donation> findAllOrderByAmountDesc();

    List<Donation> findAllByCustomerIdOrderByDateOfReceiptDesc(long customerId);
}
