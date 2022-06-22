package fontys.sem3.hpfapi.business.impl;

import fontys.sem3.hpfapi.dto.UserDTO;
import fontys.sem3.hpfapi.repository.entity.User;

final class UserDTOConverter {
    private UserDTOConverter() { }

    public static UserDTO convertToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .avatar(user.getAvatar())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }
}
