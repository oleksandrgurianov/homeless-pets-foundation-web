package fontys.sem3.hpfapi.business;

import fontys.sem3.hpfapi.dto.CreateCustomerRequestDTO;
import fontys.sem3.hpfapi.dto.CreateCustomerResponseDTO;

public interface CreateCustomerUseCase {
    CreateCustomerResponseDTO createCustomer(CreateCustomerRequestDTO request);
}
