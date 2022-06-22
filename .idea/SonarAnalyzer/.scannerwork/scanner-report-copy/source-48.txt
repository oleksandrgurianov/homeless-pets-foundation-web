package fontys.sem3.hpfapi.business.impl;

import fontys.sem3.hpfapi.business.GetAdministratorUseCase;
import fontys.sem3.hpfapi.dto.AdministratorDTO;
import fontys.sem3.hpfapi.repository.AdministratorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GetAdministratorUseCaseImpl implements GetAdministratorUseCase {
    private AdministratorRepository administratorRepository;

    @Override
    public Optional<AdministratorDTO> getAdministrator(long administratorId) {
        return administratorRepository.findById(administratorId).map(AdministratorDTOConverter::convertToDTO);
    }
}
