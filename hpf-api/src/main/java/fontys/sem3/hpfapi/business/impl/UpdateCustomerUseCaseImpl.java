package fontys.sem3.hpfapi.business.impl;

import fontys.sem3.hpfapi.business.UpdateCustomerUseCase;
import fontys.sem3.hpfapi.business.exception.InvalidCustomerException;
import fontys.sem3.hpfapi.dto.UpdateCustomerAddressRequestDTO;
import fontys.sem3.hpfapi.dto.UpdateCustomerBankDetailsRequestDTO;
import fontys.sem3.hpfapi.repository.CustomerRepository;
import fontys.sem3.hpfapi.repository.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateCustomerUseCaseImpl implements UpdateCustomerUseCase {
    private final CustomerRepository customerRepository;

    @Transactional
    @Override
    public void updateCustomerAddress(UpdateCustomerAddressRequestDTO request) {
        Optional<Customer> customerOptional = customerRepository.findById(request.getId());

        if (customerOptional.isEmpty()) {
            throw new InvalidCustomerException("CUSTOMER_ID_INVALID");
        }

        Customer customer = customerOptional.get();
        customer.setStreet(request.getStreet());
        customer.setPostcode(request.getPostcode());
        customer.setCity(request.getCity());
        customerRepository.save(customer);
    }

    @Transactional
    @Override
    public void updateCustomerBankDetails(UpdateCustomerBankDetailsRequestDTO request) {
        Optional<Customer> customerOptional = customerRepository.findById(request.getId());

        if (customerOptional.isEmpty()) {
            throw new InvalidCustomerException("CUSTOMER_ID_INVALID");
        }

        Customer customer = customerOptional.get();
        customer.setCardNumber(request.getCardNumber());
        customer.setExpirationDate(request.getExpirationDate());
        customer.setCvv(request.getCvv());
        customerRepository.save(customer);
    }
}
