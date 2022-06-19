package fontys.sem3.hpfapi.business.customer.impl;

import fontys.sem3.hpfapi.business.customer.GetCustomerUseCase;
import fontys.sem3.hpfapi.business.converter.CustomerDTOConverter;
import fontys.sem3.hpfapi.dto.customer.CustomerDTO;
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
