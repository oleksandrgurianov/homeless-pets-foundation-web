package fontys.sem3.hpfapi.business.validator;

import fontys.sem3.hpfapi.business.exception.InvalidCustomerException;
import fontys.sem3.hpfapi.business.validator.impl.CustomerIdValidatorImpl;
import fontys.sem3.hpfapi.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerIdValidatorImplTest {
    @Mock
    private CustomerRepository customerRepositoryMock;

    @InjectMocks
    private CustomerIdValidatorImpl customerIdValidator;

    @Test
    void shouldThrowExceptionWhenCustomerIdIsNull() {
        assertThrows(InvalidCustomerException.class, () -> customerIdValidator.validateId(null));
        verifyNoInteractions(customerRepositoryMock);
    }

    @Test
    void shouldThrowExceptionWhenCustomerIdIsDoesNotExist() {
        when(customerRepositoryMock.existsById(10L)).thenReturn(false);

        assertThrows(InvalidCustomerException.class, () -> customerIdValidator.validateId(10L));

        verify(customerRepositoryMock).existsById(10L);
    }

    @Test
    void shouldReturnWithoutErrorsWhenCustomerIdExists() {
        when(customerRepositoryMock.existsById(10L)).thenReturn(true);

        customerIdValidator.validateId(10L);

        verify(customerRepositoryMock).existsById(10L);
    }
}
