package fontys.sem3.hpfapi.business;

import fontys.sem3.hpfapi.dto.CreateCustomerRequestDTO;
import fontys.sem3.hpfapi.dto.CreateCustomerResponseDTO;

import javax.transaction.Transactional;

public interface CreateCustomerUseCase {
    @Transactional
    CreateCustomerResponseDTO createCustomer(CreateCustomerRequestDTO request);
}
