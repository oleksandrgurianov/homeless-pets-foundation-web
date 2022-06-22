package fontys.sem3.hpfapi.business.customer.impl;

import fontys.sem3.hpfapi.business.customer.GetCustomerUseCase;
import fontys.sem3.hpfapi.business.converter.CustomerDTOConverter;
import fontys.sem3.hpfapi.business.exception.UnauthorizedDataAccessException;
import fontys.sem3.hpfapi.dto.customer.CustomerDTO;
import fontys.sem3.hpfapi.dto.login.AccessTokenDTO;
import fontys.sem3.hpfapi.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetCustomerUseCaseImpl implements GetCustomerUseCase {
    private CustomerRepository customerRepository;
    private AccessTokenDTO requestAccessToken;

    @Override
    public Optional<CustomerDTO> getCustomer(long userId) {
        if (requestAccessToken.hasRole("CUST")) {
            if (requestAccessToken.getUserId() != userId) {
                throw new UnauthorizedDataAccessException("USER_ID_NOT_FROM_LOGGED_IN_USER");
            } else {
                return customerRepository.findByUserId(userId).map(CustomerDTOConverter::convertToDTO);
            }
        } else {
            throw new UnauthorizedDataAccessException("ACCESS_DENIED");
        }
    }
}
