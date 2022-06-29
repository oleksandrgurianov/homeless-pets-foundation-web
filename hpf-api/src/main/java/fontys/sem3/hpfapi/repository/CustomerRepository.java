package fontys.sem3.hpfapi.repository;

import fontys.sem3.hpfapi.repository.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    //Customer

    Optional<Customer> findByUserId(long id);
}
