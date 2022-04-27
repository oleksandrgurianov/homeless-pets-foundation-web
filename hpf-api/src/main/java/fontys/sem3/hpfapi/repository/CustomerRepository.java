package fontys.sem3.hpfapi.repository;

import fontys.sem3.hpfapi.repository.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findAllByUserFullNameContainingAndStatusOrderByUserFullNameAsc(String fullName, Boolean status);

    List<Customer> findAllByUserFullNameContainingAndStatusOrderByUserFullNameDesc(String fullName, Boolean status);

    List<Customer> findAllByStatusOrderByUserFullNameAsc(Boolean status);

    List<Customer> findAllByStatusOrderByUserFullNameDesc(Boolean status);
}
