package fontys.sem3.hpfapi.business.customer;

import fontys.sem3.hpfapi.dto.customer.CreateCustomerRequestDTO;
import fontys.sem3.hpfapi.dto.customer.CreateCustomerResponseDTO;

public interface CreateCustomerUseCase {
    CreateCustomerResponseDTO createCustomer(CreateCustomerRequestDTO request);
}
