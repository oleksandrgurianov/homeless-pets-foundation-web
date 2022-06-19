package fontys.sem3.hpfapi.business.converter;

import fontys.sem3.hpfapi.dto.user.UserDTO;
import fontys.sem3.hpfapi.repository.entity.User;

import java.util.List;

public class UserDTOConverter {
    private UserDTOConverter() {
    }

    public static UserDTO convertToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .avatar(user.getAvatar())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .password(user.getPassword())
                .roles(List.of(user.getRole()))
                .build();
    }
}
