package fontys.sem3.hpfapi.business.customer;

import fontys.sem3.hpfapi.business.customer.impl.UpdateCustomerUseCaseImpl;
import fontys.sem3.hpfapi.business.exception.InvalidCustomerException;
import fontys.sem3.hpfapi.business.exception.UnauthorizedDataAccessException;
import fontys.sem3.hpfapi.dto.customer.UpdateCustomerAddressRequestDTO;
import fontys.sem3.hpfapi.dto.customer.UpdateCustomerBankDetailsRequestDTO;
import fontys.sem3.hpfapi.dto.login.AccessTokenDTO;
import fontys.sem3.hpfapi.repository.CustomerRepository;
import fontys.sem3.hpfapi.repository.entity.Customer;
import fontys.sem3.hpfapi.repository.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class UpdateCustomerUseCaseImplTest {
    @Mock
    private CustomerRepository customerRepositoryMock;

    @Mock
    private AccessTokenDTO requestAccessToken;

    @InjectMocks
    private UpdateCustomerUseCaseImpl updateCustomerUseCase;

    @Test
    void shouldThrowInvalidCustomerExceptionWhenUserIdInvalid1() {
        when(customerRepositoryMock.findByUserId(2L)).thenReturn(Optional.empty());

        UpdateCustomerAddressRequestDTO request = UpdateCustomerAddressRequestDTO.builder().userId(2L).build();
        assertThrows(InvalidCustomerException.class,
                () -> updateCustomerUseCase.updateCustomerAddress(request));

        verify(customerRepositoryMock).findByUserId(2L);
        verifyNoMoreInteractions(customerRepositoryMock);
    }

    @Test
    void shouldThrowUnauthorizedDataAccessExceptionWhenUserRoleNotMatched1() {
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

        UpdateCustomerAddressRequestDTO request = UpdateCustomerAddressRequestDTO.builder().userId(2L).build();

        when(requestAccessToken.hasRole("CUST"))
                .thenReturn(false);

        UnauthorizedDataAccessException unauthorizedDataAccessException = assertThrows(UnauthorizedDataAccessException.class,
                () -> updateCustomerUseCase.updateCustomerAddress(request));

        assertEquals("ACCESS_DENIED", unauthorizedDataAccessException.getReason());

        verify(customerRepositoryMock).findByUserId(2L);
        verifyNoMoreInteractions(customerRepositoryMock);
        verify(requestAccessToken).hasRole("CUST");
    }

    @Test
    void shouldThrowUnauthorizedDataAccessExceptionWhenUserIdNotMatched1() {
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

        UpdateCustomerAddressRequestDTO request = UpdateCustomerAddressRequestDTO.builder().userId(2L).build();

        when(requestAccessToken.hasRole("CUST"))
                .thenReturn(true);
        when(requestAccessToken.getUserId())
                .thenReturn(3L);

        UnauthorizedDataAccessException unauthorizedDataAccessException = assertThrows(UnauthorizedDataAccessException.class,
                () -> updateCustomerUseCase.updateCustomerAddress(request));

        assertEquals("USER_ID_NOT_FROM_LOGGED_IN_USER", unauthorizedDataAccessException.getReason());

        verify(customerRepositoryMock).findByUserId(2L);
        verifyNoMoreInteractions(customerRepositoryMock);
        verify(requestAccessToken).hasRole("CUST");
        verify(requestAccessToken).getUserId();
    }

    @Test
    void shouldUpdateCustomerAddress() {
        Customer customerBeforeUpdate = Customer.builder()
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
        when(customerRepositoryMock.findByUserId(2L)).thenReturn(Optional.of(customerBeforeUpdate));

        UpdateCustomerAddressRequestDTO request = UpdateCustomerAddressRequestDTO.builder()
                .userId(2L)
                .street("Parkweg 132")
                .postcode("6511 BG")
                .city("Nijmegen")
                .build();

        when(requestAccessToken.hasRole("CUST"))
                .thenReturn(true);
        when(requestAccessToken.getUserId())
                .thenReturn(2L);

        updateCustomerUseCase.updateCustomerAddress(request);

        verify(customerRepositoryMock).findByUserId(2L);

        Customer expectedSavedCustomer = Customer.builder()
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
                .street("Parkweg 132")
                .postcode("6511 BG")
                .city("Nijmegen")
                .cardNumber("5561036905645903")
                .expirationDate("05/26")
                .cvv("677")
                .build();
        verify(customerRepositoryMock).save(expectedSavedCustomer);
        verify(requestAccessToken).hasRole("CUST");
        verify(requestAccessToken).getUserId();
    }

    @Test
    void shouldThrowInvalidCustomerExceptionWhenUserIdInvalid2() {
        when(customerRepositoryMock.findByUserId(2L)).thenReturn(Optional.empty());

        UpdateCustomerBankDetailsRequestDTO request = UpdateCustomerBankDetailsRequestDTO.builder().userId(2L).build();
        assertThrows(InvalidCustomerException.class,
                () -> updateCustomerUseCase.updateCustomerBankDetails(request));

        verify(customerRepositoryMock).findByUserId(2L);
        verifyNoMoreInteractions(customerRepositoryMock);
    }

    @Test
    void shouldThrowUnauthorizedDataAccessExceptionWhenUserRoleNotMatched2() {
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

        UpdateCustomerBankDetailsRequestDTO request = UpdateCustomerBankDetailsRequestDTO.builder().userId(2L).build();

        when(requestAccessToken.hasRole("CUST"))
                .thenReturn(false);

        UnauthorizedDataAccessException unauthorizedDataAccessException = assertThrows(UnauthorizedDataAccessException.class,
                () -> updateCustomerUseCase.updateCustomerBankDetails(request));

        assertEquals("ACCESS_DENIED", unauthorizedDataAccessException.getReason());

        verify(customerRepositoryMock).findByUserId(2L);
        verifyNoMoreInteractions(customerRepositoryMock);
        verify(requestAccessToken).hasRole("CUST");
    }

    @Test
    void shouldThrowUnauthorizedDataAccessExceptionWhenUserIdNotMatched2() {
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

        UpdateCustomerBankDetailsRequestDTO request = UpdateCustomerBankDetailsRequestDTO.builder().userId(2L).build();

        when(requestAccessToken.hasRole("CUST"))
                .thenReturn(true);
        when(requestAccessToken.getUserId())
                .thenReturn(3L);

        UnauthorizedDataAccessException unauthorizedDataAccessException = assertThrows(UnauthorizedDataAccessException.class,
                () -> updateCustomerUseCase.updateCustomerBankDetails(request));

        assertEquals("USER_ID_NOT_FROM_LOGGED_IN_USER", unauthorizedDataAccessException.getReason());

        verify(customerRepositoryMock).findByUserId(2L);
        verifyNoMoreInteractions(customerRepositoryMock);
        verify(requestAccessToken).hasRole("CUST");
        verify(requestAccessToken).getUserId();
    }

    @Test
    void shouldUpdateCustomerBankDetails() {
        Customer customerBeforeUpdate = Customer.builder()
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
        when(customerRepositoryMock.findByUserId(2L)).thenReturn(Optional.of(customerBeforeUpdate));

        UpdateCustomerBankDetailsRequestDTO request = UpdateCustomerBankDetailsRequestDTO.builder()
                .userId(2L)
                .cardNumber("5327789050901654")
                .expirationDate("05/26")
                .cvv("170")
                .build();

        when(requestAccessToken.hasRole("CUST"))
                .thenReturn(true);
        when(requestAccessToken.getUserId())
                .thenReturn(2L);

        updateCustomerUseCase.updateCustomerBankDetails(request);

        verify(customerRepositoryMock).findByUserId(2L);

        Customer expectedSavedCustomer = Customer.builder()
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
                .cardNumber("5327789050901654")
                .expirationDate("05/26")
                .cvv("170")
                .build();
        verify(customerRepositoryMock).save(expectedSavedCustomer);
        verify(requestAccessToken).hasRole("CUST");
        verify(requestAccessToken).getUserId();
    }
}
