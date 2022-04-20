package fontys.sem3.hpfapi.business;

import fontys.sem3.hpfapi.dto.UserDTO;
import java.util.ArrayList;

public interface UserService {
    UserDTO getUserByEmailAndPassword(String email, String password);
    ArrayList<UserDTO> getSortedCustomersBySearchAndStatus(String search, boolean ascending, boolean approved);
    UserDTO getUserById(int id);
    boolean createUser(UserDTO user);
    boolean updateAvatar(UserDTO user);
    boolean updateUser(UserDTO user);
    boolean updateCard(UserDTO user);
    boolean deleteAvatar(UserDTO user);
    boolean deleteCustomer(UserDTO user);
    boolean deleteCard(UserDTO user);
    boolean updateStatus(UserDTO user);
}
