package fontys.sem3.hpfapi.business.validator;

import fontys.sem3.hpfapi.business.exception.InvalidPetException;
import fontys.sem3.hpfapi.business.validator.impl.PetIdValidatorImpl;
import fontys.sem3.hpfapi.repository.PetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetIdValidatorImplTest {
    @Mock
    private PetRepository petRepositoryMock;

    @InjectMocks
    private PetIdValidatorImpl petIdValidator;

    @Test
    void shouldThrowExceptionWhenCountryIdIsNull() {
        assertThrows(InvalidPetException.class, () -> petIdValidator.validateId(null));
        verifyNoInteractions(petRepositoryMock);
    }

    @Test
    void shouldThrowExceptionWhenCountryIdIsDoesNotExist() {
        when(petRepositoryMock.existsById(10L)).thenReturn(false);

        assertThrows(InvalidPetException.class, () -> petIdValidator.validateId(10L));

        verify(petRepositoryMock).existsById(10L);
    }

    @Test
    void shouldReturnWithoutErrorsWhenCountryIdExists() {
        when(petRepositoryMock.existsById(10L)).thenReturn(true);

        petIdValidator.validateId(10L);

        verify(petRepositoryMock).existsById(10L);
    }
}
