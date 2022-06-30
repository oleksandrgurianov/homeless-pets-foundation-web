package fontys.sem3.hpfapi.business.pet;

import fontys.sem3.hpfapi.business.exception.UnauthorizedDataAccessException;
import fontys.sem3.hpfapi.business.pet.impl.CreatePetUseCaseImpl;
import fontys.sem3.hpfapi.business.pet.impl.DeletePetUseCaseImpl;
import fontys.sem3.hpfapi.dto.login.AccessTokenDTO;
import fontys.sem3.hpfapi.dto.pet.CreatePetRequestDTO;
import fontys.sem3.hpfapi.repository.PetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeletePetUseCaseImplTest {
    @Mock
    private PetRepository petRepositoryMock;

    @Mock
    private AccessTokenDTO requestAccessToken;

    @InjectMocks
    private DeletePetUseCaseImpl deletePetUseCase;

    @Test
    void shouldThrowUnauthorizedDataAccessExceptionWhenUserRoleNotMatched() {
        when(requestAccessToken.hasRole("ADMIN"))
                .thenReturn(false);

        UnauthorizedDataAccessException unauthorizedDataAccessException = assertThrows(UnauthorizedDataAccessException.class,
                () -> deletePetUseCase.deletePet(1L));

        assertEquals("ACCESS_DENIED", unauthorizedDataAccessException.getReason());

        verify(requestAccessToken).hasRole("ADMIN");
    }

    @Test
    void shouldDeletePet() {
        when(requestAccessToken.hasRole("ADMIN"))
                .thenReturn(true);

        deletePetUseCase.deletePet(1L);

        verify(requestAccessToken).hasRole("ADMIN");
        verify(petRepositoryMock).deleteById(1L);
    }
}
