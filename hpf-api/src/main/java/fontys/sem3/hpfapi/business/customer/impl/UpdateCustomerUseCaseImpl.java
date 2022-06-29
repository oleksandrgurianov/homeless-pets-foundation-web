package fontys.sem3.hpfapi.business.customer.impl;

import fontys.sem3.hpfapi.business.customer.UpdateCustomerUseCase;
import fontys.sem3.hpfapi.business.exception.InvalidCustomerException;
import fontys.sem3.hpfapi.business.exception.UnauthorizedDataAccessException;
import fontys.sem3.hpfapi.dto.customer.UpdateCustomerAddressRequestDTO;
import fontys.sem3.hpfapi.dto.customer.UpdateCustomerBankDetailsRequestDTO;
import fontys.sem3.hpfapi.dto.login.AccessTokenDTO;
import fontys.sem3.hpfapi.repository.CustomerRepository;
import fontys.sem3.hpfapi.repository.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateCustomerUseCaseImpl implements UpdateCustomerUseCase {
    private final CustomerRepository customerRepository;
    private final AccessTokenDTO requestAccessToken;

    @Transactional
    @Override
    public void updateCustomerAddress(UpdateCustomerAddressRequestDTO request) {
        Optional<Customer> customerOptional = customerRepository.findByUserId(request.getUserId());

        if (customerOptional.isEmpty()) {
            throw new InvalidCustomerException("USER_ID_INVALID");
        } else {
            Customer customer = customerOptional.get();

            if (requestAccessToken.hasRole("CUST")) {
                if (!Objects.equals(requestAccessToken.getUserId(), customerOptional.get().getUser().getId())) {
                    throw new UnauthorizedDataAccessException("USER_ID_NOT_FROM_LOGGED_IN_USER");
                } else {
                    customer.setStreet(request.getStreet());
                    customer.setPostcode(request.getPostcode());
                    customer.setCity(request.getCity());
                    customerRepository.save(customer);
                }
            } else {
                throw new UnauthorizedDataAccessException("ACCESS_DENIED");
            }
        }
    }

    @Transactional
    @Override
    public void updateCustomerBankDetails(UpdateCustomerBankDetailsRequestDTO request) {
        Optional<Customer> customerOptional = customerRepository.findByUserId(request.getUserId());

        if (customerOptional.isEmpty()) {
            throw new InvalidCustomerException("USER_ID_INVALID");
        } else {
            Customer customer = customerOptional.get();

            if (requestAccessToken.hasRole("CUST")) {
                if (!Objects.equals(requestAccessToken.getUserId(), customerOptional.get().getUser().getId())) {
                    throw new UnauthorizedDataAccessException("USER_ID_NOT_FROM_LOGGED_IN_USER");
                } else {
                    customer.setCardNumber(request.getCardNumber());
                    customer.setExpirationDate(request.getExpirationDate());
                    customer.setCvv(request.getCvv());
                    customerRepository.save(customer);
                }
            } else {
                throw new UnauthorizedDataAccessException("ACCESS_DENIED");
            }
        }
    }
}
