package fontys.sem3.hpfapi.business.customer;

import fontys.sem3.hpfapi.business.customer.impl.GetCustomerUseCaseImpl;
import fontys.sem3.hpfapi.business.exception.UnauthorizedDataAccessException;
import fontys.sem3.hpfapi.dto.customer.CustomerDTO;
import fontys.sem3.hpfapi.dto.login.AccessTokenDTO;
import fontys.sem3.hpfapi.dto.user.UserDTO;
import fontys.sem3.hpfapi.repository.CustomerRepository;
import fontys.sem3.hpfapi.repository.entity.Customer;
import fontys.sem3.hpfapi.repository.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetCustomerUseCaseImplTest {
    @Mock
    private CustomerRepository customerRepositoryMock;

    @Mock
    private AccessTokenDTO requestAccessToken;

    @InjectMocks
    private GetCustomerUseCaseImpl getCustomerUseCase;

    @Test
    void shouldThrowUnauthorizedDataAccessExceptionWhenUserRoleNotMatched() {
        when(requestAccessToken.hasRole("CUST"))
                .thenReturn(false);

        UnauthorizedDataAccessException unauthorizedDataAccessException = assertThrows(UnauthorizedDataAccessException.class,
                () -> getCustomerUseCase.getCustomer(2L));

        assertEquals("ACCESS_DENIED", unauthorizedDataAccessException.getReason());

        verify(requestAccessToken).hasRole("CUST");
    }

    @Test
    void shouldThrowUnauthorizedDataAccessExceptionWhenUserIdNotMatched() {
        when(requestAccessToken.hasRole("CUST"))
                .thenReturn(true);
        when(requestAccessToken.getUserId())
                .thenReturn(3L);

        UnauthorizedDataAccessException unauthorizedDataAccessException = assertThrows(UnauthorizedDataAccessException.class,
                () -> getCustomerUseCase.getCustomer(2L));

        assertEquals("USER_ID_NOT_FROM_LOGGED_IN_USER", unauthorizedDataAccessException.getReason());

        verify(requestAccessToken).hasRole("CUST");
        verify(requestAccessToken).getUserId();
    }

    @Test
    void shouldReturnCustomerIfFound() {
        Customer customer = Customer.builder()
                .id(1L)
                .user(User.builder()
                        .id(2L)
                        .avatar("https://drive.google.com/uc?export=view&id=1Rm-dceeyImW5JIBJGBrMiNKUzegRZ_Qo")
                        .fullName("Pascal Broeks")
                        .email("cust1@gmail.com")
                        .phoneNumber("0651535133")
                        .password("password")
                        .role("CUST")
                        .build())
                .street("Singel 57")
                .postcode("3311 HP")
                .city("Dordrecht")
                .cardNumber("5561036905645903")
                .expirationDate("05/26")
                .cvv("677")
                .build();
        when(customerRepositoryMock.findByUserId(2L))
                .thenReturn(Optional.of(customer));

        when(requestAccessToken.hasRole("CUST"))
                .thenReturn(true);
        when(requestAccessToken.getUserId())
                .thenReturn(2L);

        Optional<CustomerDTO> customerOptional = getCustomerUseCase.getCustomer(2L);
        CustomerDTO actualDTO = customerOptional.orElseThrow();

        CustomerDTO expectedDTO = CustomerDTO.builder()
                .id(1L)
                .user(UserDTO.builder()
                        .id(2L)
                        .avatar("https://drive.google.com/uc?export=view&id=1Rm-dceeyImW5JIBJGBrMiNKUzegRZ_Qo")
                        .fullName("Pascal Broeks")
                        .email("cust1@gmail.com")
                        .phoneNumber("0651535133")
                        .password("password")
                        .roles(List.of("CUST"))
                        .build())
                .street("Singel 57")
                .postcode("3311 HP")
                .city("Dordrecht")
                .cardNumber("5561036905645903")
                .expirationDate("05/26")
                .cvv("677")
                .build();
        assertEquals(expectedDTO, actualDTO);

        verify(customerRepositoryMock).findByUserId(2L);
        verify(requestAccessToken).hasRole("CUST");
        verify(requestAccessToken).getUserId();
    }
}
