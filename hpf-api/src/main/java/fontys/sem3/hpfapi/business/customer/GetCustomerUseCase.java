package fontys.sem3.hpfapi.business.customer;

import fontys.sem3.hpfapi.dto.customer.CustomerDTO;

import java.util.Optional;

public interface GetCustomerUseCase {
    Optional<CustomerDTO> getCustomer(long userId);
}
