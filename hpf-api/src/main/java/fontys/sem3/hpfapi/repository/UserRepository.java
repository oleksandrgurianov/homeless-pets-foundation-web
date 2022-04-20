package fontys.sem3.hpfapi.repository;

import fontys.sem3.hpfapi.dto.UserDTO;
import java.util.ArrayList;

public interface UserRepository {
    UserDTO getUserByEmailAndPassword(String email, String password);
    UserDTO getUserById(int id);
    ArrayList<UserDTO> getSortedCustomersBySearchAndStatus(String search, boolean ascending, boolean approved);
    boolean userExists(UserDTO user);
    void createUser(UserDTO user);
    boolean updateAvatar(UserDTO user);
    boolean updateUser(UserDTO user);
    boolean updateCard(UserDTO user);
    boolean deleteAvatar(UserDTO user);
    boolean deleteCustomer(UserDTO user);
    boolean deleteCard(UserDTO user);
    boolean updateStatus(UserDTO user);
}
