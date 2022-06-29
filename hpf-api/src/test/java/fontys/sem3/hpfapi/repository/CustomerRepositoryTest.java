package fontys.sem3.hpfapi.repository;

import fontys.sem3.hpfapi.repository.entity.Customer;
import fontys.sem3.hpfapi.repository.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class CustomerRepositoryTest {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void findByUserId_shouldReturnCustomer_whenItExists() {
        User user1 = User.builder()
                .avatar("https://drive.google.com/uc?export=view&id=1Rm-dceeyImW5JIBJGBrMiNKUzegRZ_Qo")
                .fullName("Pascal Broeks")
                .email("cust1@gmail.com")
                .phoneNumber("0651535133")
                .password("password")
                .role("CUST")
                .build();
        entityManager.persist(user1);

        entityManager.persist(Customer.builder()
                .user(user1)
                .street("Singel 57")
                .postcode("3311 HP")
                .city("Dordrecht")
                .cardNumber("5561036905645903")
                .expirationDate("05/26")
                .cvv("677")
                .build());

        Optional<Customer> actualCustomer = customerRepository.findByUserId(1L);

        assertTrue(actualCustomer.isPresent());
        Optional<Customer> expectedCustomer = Optional.of(Customer.builder()
                .user(user1)
                .street("Singel 57")
                .postcode("3311 HP")
                .city("Dordrecht")
                .cardNumber("5561036905645903")
                .expirationDate("05/26")
                .cvv("677")
                .build());
        assertThat(actualCustomer)
                .usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .isEqualTo(expectedCustomer);
    }

    @Test
    void findByUserId_shouldReturnNull_whenCustomerNotFound() {
        Optional<Customer> actualCustomer = customerRepository.findByUserId(101010L);
        assertFalse(actualCustomer.isPresent());
    }
}
