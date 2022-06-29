package fontys.sem3.hpfapi.repository;

import fontys.sem3.hpfapi.repository.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {
    //All

    @Query("SELECT SUM(amount) FROM Donation")
    Double sumAll();


    //Customer

    List<Donation> findAllByCustomerUserIdOrderByDateOfReceiptDesc(Long userId);


    //Administrator

    List<Donation> findAllByOrderByDateOfReceiptDesc();
}
