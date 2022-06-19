package fontys.sem3.hpfapi.business.converter;

import fontys.sem3.hpfapi.dto.administrator.AdministratorDTO;
import fontys.sem3.hpfapi.repository.entity.Administrator;

public class AdministratorDTOConverter {
    private AdministratorDTOConverter() {
    }

    public static AdministratorDTO convertToDTO(Administrator administrator) {
        return AdministratorDTO.builder()
                .id(administrator.getId())
                .jobPosition(administrator.getJobPosition())
                .user(UserDTOConverter.convertToDTO(administrator.getUser()))
                .build();
    }
}
