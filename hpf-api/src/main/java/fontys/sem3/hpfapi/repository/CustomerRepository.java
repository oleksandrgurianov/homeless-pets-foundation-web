package fontys.sem3.hpfapi.repository;

import fontys.sem3.hpfapi.repository.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    //Administrator

    List<Customer> findAllByStatusOrderByUser_FullNameAsc(Boolean status);

    List<Customer> findAllByStatusOrderByUser_FullNameDesc(Boolean status);

    List<Customer> findAllByUser_FullNameContainingAndStatusOrderByUser_FullNameAsc(String fullName, Boolean status);

    List<Customer> findAllByUser_FullNameContainingAndStatusOrderByUser_FullNameDesc(String fullName, Boolean status);
}
