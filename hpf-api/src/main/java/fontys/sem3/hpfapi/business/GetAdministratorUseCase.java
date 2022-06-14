package fontys.sem3.hpfapi.business;

import fontys.sem3.hpfapi.dto.AdministratorDTO;

import java.util.Optional;

public interface GetAdministratorUseCase {
    Optional<AdministratorDTO> getAdministrator(long administratorId);
}
