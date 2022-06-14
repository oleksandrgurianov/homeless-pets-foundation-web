package fontys.sem3.hpfapi.business.impl;

import fontys.sem3.hpfapi.business.GetCustomerUseCase;
import fontys.sem3.hpfapi.dto.CustomerDTO;
import fontys.sem3.hpfapi.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetCustomerUseCaseImpl implements GetCustomerUseCase {
    private CustomerRepository customerRepository;

    @Override
    public Optional<CustomerDTO> getCustomer(long customerId) {
        return customerRepository.findById(customerId).map(CustomerDTOConverter::convertToDTO);
    }
}
