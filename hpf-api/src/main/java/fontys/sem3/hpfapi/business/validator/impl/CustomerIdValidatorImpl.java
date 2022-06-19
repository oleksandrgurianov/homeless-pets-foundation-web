package fontys.sem3.hpfapi.business.validator.impl;

import fontys.sem3.hpfapi.business.validator.CustomerIdValidator;
import fontys.sem3.hpfapi.business.exception.InvalidCustomerException;
import fontys.sem3.hpfapi.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerIdValidatorImpl implements CustomerIdValidator {
    private final CustomerRepository customerRepository;

    @Override
    public void validateId(Long customerId) {
        if (customerId == null || !customerRepository.existsById(customerId)) {
            throw new InvalidCustomerException();
        }
    }
}
