package fontys.sem3.hpfapi.business.administrator.impl;

import fontys.sem3.hpfapi.business.administrator.GetAdministratorUseCase;
import fontys.sem3.hpfapi.business.converter.AdministratorDTOConverter;
import fontys.sem3.hpfapi.dto.administrator.AdministratorDTO;
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
