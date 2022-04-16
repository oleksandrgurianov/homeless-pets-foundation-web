package fontys.sem3.hpfapi.repository;

import fontys.sem3.hpfapi.dto.UserDTO;
import java.util.List;

public interface UserRepository {
    List<UserDTO> getUsers();
}
