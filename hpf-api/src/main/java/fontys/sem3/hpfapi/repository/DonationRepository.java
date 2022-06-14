package fontys.sem3.hpfapi.repository;

import fontys.sem3.hpfapi.repository.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {
    //All

    @Query("SELECT SUM(amount) FROM Donation")
    Double sumAll();


    //Customer

    List<Donation> findAllByCustomerIdOrderByDateOfReceiptDesc(Long customerId);


    //Administrator
    List<Donation> findAllByOrderByDateOfReceiptAsc();

    List<Donation> findAllByOrderByDateOfReceiptDesc();

    List<Donation> findAllByOrderByAmountAsc();

    List<Donation> findAllByOrderByAmountDesc();

    List<Donation> findAllByDateOfReceiptBetweenOrderByDateOfReceiptAsc(Date startDate, Date endDate);

    List<Donation> findAllByDateOfReceiptBetweenOrderByDateOfReceiptDesc(Date startDate, Date endDate);

    List<Donation> findAllByDateOfReceiptBetweenOrderByAmountAsc(Date startDate, Date endDate);

    List<Donation> findAllByDateOfReceiptBetweenOrderByAmountDesc(Date startDate, Date endDate);
}
