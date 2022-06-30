package fontys.sem3.hpfapi.business.pet;

import fontys.sem3.hpfapi.business.exception.InvalidCustomerException;
import fontys.sem3.hpfapi.business.exception.InvalidPetException;
import fontys.sem3.hpfapi.business.exception.UnauthorizedDataAccessException;
import fontys.sem3.hpfapi.business.pet.impl.UpdatePetUseCaseImpl;
import fontys.sem3.hpfapi.business.validator.CustomerIdValidator;
import fontys.sem3.hpfapi.dto.customer.UpdateCustomerAddressRequestDTO;
import fontys.sem3.hpfapi.dto.login.AccessTokenDTO;
import fontys.sem3.hpfapi.dto.pet.UpdatePetCustomerRequestDTO;
import fontys.sem3.hpfapi.repository.PetRepository;
import fontys.sem3.hpfapi.repository.entity.Customer;
import fontys.sem3.hpfapi.repository.entity.Pet;
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
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UpdatePetUseCaseImplTest {
    @Mock
    private PetRepository petRepositoryMock;

    @Mock
    private AccessTokenDTO requestAccessToken;

    @Mock
    private CustomerIdValidator customerIdValidatorMock;

    @InjectMocks
    private UpdatePetUseCaseImpl updatePetUseCase;

    @Test
    void shouldThrowUnauthorizedDataAccessExceptionWhenUserRoleNotMatched() {
        when(requestAccessToken.hasRole("CUST"))
                .thenReturn(false);

        UpdatePetCustomerRequestDTO request = UpdatePetCustomerRequestDTO.builder().build();
        UnauthorizedDataAccessException unauthorizedDataAccessException = assertThrows(UnauthorizedDataAccessException.class,
                () -> updatePetUseCase.updatePetCustomer(request));

        assertEquals("ACCESS_DENIED", unauthorizedDataAccessException.getReason());

        verify(requestAccessToken).hasRole("CUST");
    }

    @Test
    void shouldThrowInvalidPetExceptionWhenPetIdInvalid() {
        when(requestAccessToken.hasRole("CUST"))
                .thenReturn(true);
        when(petRepositoryMock.findByIdAndCustomerIsNull(1L)).thenReturn(Optional.empty());

        UpdatePetCustomerRequestDTO request = UpdatePetCustomerRequestDTO.builder().id(1L).customerId(1L).build();
        assertThrows(InvalidPetException.class,
                () -> updatePetUseCase.updatePetCustomer(request));

        verify(petRepositoryMock).findByIdAndCustomerIsNull(1L);
        verifyNoMoreInteractions(petRepositoryMock);
        verify(requestAccessToken).hasRole("CUST");
    }

    @Test
    void shouldThrowInvalidCustomerExceptionWhenCustomerIdInvalid() {
        Pet pet = Pet.builder()
                .id(1L)
                .customer(null)
                .icon("https://drive.google.com/uc?export=view&id=18G-f5b2AOrHx5DNUVxWSxXYz1TdCt4y8")
                .type("DOGS")
                .name("Jamison")
                .breed("Terrier Mix")
                .ageCategory("Young")
                .gender("Male")
                .size("Large")
                .color("Brown/Chocolate, White/Cream")
                .description("Meet Jamison! He is a 2 year old terrier mix. This handsome guy just loves people and gets the happiest wiggly butt of excitement. Great on the leash and knows basic commands.")
                .adoptionFee(250d)
                .build();
        when(petRepositoryMock.findByIdAndCustomerIsNull(1L)).thenReturn(Optional.of(pet));

        when(requestAccessToken.hasRole("CUST"))
                .thenReturn(true);

        doThrow(InvalidCustomerException.class)
                .when(customerIdValidatorMock).validateId(1L);

        UpdatePetCustomerRequestDTO request = UpdatePetCustomerRequestDTO.builder().id(1L).customerId(1L).build();
        assertThrows(InvalidCustomerException.class,
                () -> updatePetUseCase.updatePetCustomer(request));

        verify(petRepositoryMock).findByIdAndCustomerIsNull(1L);
        verifyNoMoreInteractions(petRepositoryMock);
        verify(customerIdValidatorMock).validateId(1L);
        verify(requestAccessToken).hasRole("CUST");
    }

    @Test
    void shouldUpdatePetCustomer() {
        Pet petBeforeUpdate = Pet.builder()
                .id(1L)
                .customer(null)
                .icon("https://drive.google.com/uc?export=view&id=18G-f5b2AOrHx5DNUVxWSxXYz1TdCt4y8")
                .type("DOGS")
                .name("Jamison")
                .breed("Terrier Mix")
                .ageCategory("Young")
                .gender("Male")
                .size("Large")
                .color("Brown/Chocolate, White/Cream")
                .description("Meet Jamison! He is a 2 year old terrier mix. This handsome guy just loves people and gets the happiest wiggly butt of excitement. Great on the leash and knows basic commands.")
                .adoptionFee(250d)
                .build();
        when(petRepositoryMock.findByIdAndCustomerIsNull(1L)).thenReturn(Optional.of(petBeforeUpdate));

        UpdatePetCustomerRequestDTO request = UpdatePetCustomerRequestDTO.builder().id(1L).customerId(1L).build();

        when(requestAccessToken.hasRole("CUST"))
                .thenReturn(true);

        updatePetUseCase.updatePetCustomer(request);

        verify(petRepositoryMock).findByIdAndCustomerIsNull(1L);

        Pet expectedSavedPet = Pet.builder()
                .id(1L)
                .customer(Customer.builder().id(1L).build())
                .icon("https://drive.google.com/uc?export=view&id=18G-f5b2AOrHx5DNUVxWSxXYz1TdCt4y8")
                .type("DOGS")
                .name("Jamison")
                .breed("Terrier Mix")
                .ageCategory("Young")
                .gender("Male")
                .size("Large")
                .color("Brown/Chocolate, White/Cream")
                .description("Meet Jamison! He is a 2 year old terrier mix. This handsome guy just loves people and gets the happiest wiggly butt of excitement. Great on the leash and knows basic commands.")
                .adoptionFee(250d)
                .build();
        verify(petRepositoryMock).save(expectedSavedPet);
        verify(requestAccessToken).hasRole("CUST");
    }
}
