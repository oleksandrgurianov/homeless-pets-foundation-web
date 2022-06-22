package fontys.sem3.hpfapi.business;

import fontys.sem3.hpfapi.dto.CustomerDTO;
import java.util.Optional;

public interface GetCustomerUseCase {
    Optional<CustomerDTO> getCustomer(long customerId);
}
