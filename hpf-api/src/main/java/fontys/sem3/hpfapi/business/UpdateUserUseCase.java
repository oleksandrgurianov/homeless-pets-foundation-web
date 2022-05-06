package fontys.sem3.hpfapi.business;

import fontys.sem3.hpfapi.dto.UpdateUserAvatarRequestDTO;
import fontys.sem3.hpfapi.dto.UpdateUserDetailsRequestDTO;
import fontys.sem3.hpfapi.dto.UpdateUserPasswordRequestDTO;

public interface UpdateUserUseCase {
    void updateUserAvatar(UpdateUserAvatarRequestDTO request);

    void updateUserDetails(UpdateUserDetailsRequestDTO request);

    void updateUserPassword(UpdateUserPasswordRequestDTO request);
}
