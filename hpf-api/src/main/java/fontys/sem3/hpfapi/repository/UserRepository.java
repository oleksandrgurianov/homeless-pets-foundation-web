package fontys.sem3.hpfapi.repository;

import fontys.sem3.hpfapi.dto.UserDTO;
import java.util.ArrayList;

public interface UserRepository {
    UserDTO getUserByEmailAndPassword(String email, String password);
    UserDTO getUserById(int id);
    ArrayList<UserDTO> getCustomersBySearch(String search);
    ArrayList<UserDTO> getCustomersSortedByFullName(boolean ascending);
}
