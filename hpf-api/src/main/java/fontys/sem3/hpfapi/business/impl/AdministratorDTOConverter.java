package fontys.sem3.hpfapi.business.impl;

import fontys.sem3.hpfapi.dto.AdministratorDTO;
import fontys.sem3.hpfapi.repository.entity.Administrator;

final class AdministratorDTOConverter {
    private AdministratorDTOConverter() { }

    public static AdministratorDTO convertToDTO(Administrator administrator) {
        return AdministratorDTO.builder()
                .id(administrator.getId())
                .jobPosition(administrator.getJobPosition())
                .user(UserDTOConverter.convertToDTO(administrator.getUser()))
                .build();
    }
}
