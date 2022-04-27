package fontys.sem3.hpfapi.business.impl;

import fontys.sem3.hpfapi.business.UserService;
import fontys.sem3.hpfapi.dto.CustomerDTO;
import fontys.sem3.hpfapi.dto.UserDTO;
import fontys.sem3.hpfapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Primary
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    /*
    @Override
    public UserDTO getUserByEmailAndPassword(String email, String password) {
        if (!email.isBlank() && !password.isBlank()) {
            return this.userRepository.getUserByEmailAndPassword(email, password);
        }

        return null;
    }

    @Override
    public ArrayList<UserDTO> getSortedCustomersBySearchAndStatus(String search, boolean ascending, boolean approved) {
        return this.userRepository.getSortedCustomersBySearchAndStatus(search, ascending, approved);
    }

    @Override
    public UserDTO getUserById(int id) {
        return this.userRepository.getUserById(id);
    }

    @Override
    public boolean createUser(UserDTO user) {
        if (user != null) {
            if (!user.getFullName().isBlank() && !user.getPhoneNumber().isBlank() && !user.getEmail().isBlank() && !user.getPassword().isBlank()) {
                if (!this.userRepository.userExists(user)) {
                    this.userRepository.createUser(user);
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean updateAvatar(UserDTO user) {
        if (user != null) {
            if (!user.getAvatar().isBlank()) {
                return this.userRepository.updateAvatar(user);
            }
        }

        return false;
    }

    @Override
    public boolean updateUser(UserDTO user) {
        if (user != null) {
            if (!user.getFullName().isBlank() && !user.getPhoneNumber().isBlank() && !user.getEmail().isBlank() && !user.getPassword().isBlank()) {
                if (!this.userRepository.userExists(user)) {
                    return this.userRepository.updateUser(user);
                }
            }
        }

        return false;
    }

    @Override
    public boolean updateCard(UserDTO user) {
        if (user != null) {
            if (!((CustomerDTO) user).getCardNumber().isBlank() && !((CustomerDTO) user).getExpirationDate().isBlank() && !((CustomerDTO) user).getCvv().isBlank()) {
                return this.userRepository.updateUser(user);
            }
        }

        return false;
    }

    @Override
    public boolean deleteAvatar(UserDTO user) {
        if (user != null) {
            if (!user.getAvatar().isBlank()) {
                return this.userRepository.deleteAvatar(user);
            }
        }

        return false;
    }

    @Override
    public boolean deleteCustomer(UserDTO user) {
        if (user != null) {
            return this.userRepository.deleteCustomer(user);
        }

        return false;
    }

    @Override
    public boolean deleteCard(UserDTO user) {
        if (user != null) {
            if (!((CustomerDTO) user).getCardNumber().isBlank() && !((CustomerDTO) user).getExpirationDate().isBlank() && !((CustomerDTO) user).getCvv().isBlank()) {
                return this.userRepository.deleteCard(user);
            }
        }

        return false;
    }

    @Override
    public boolean updateStatus(UserDTO user) {
        if (user != null) {
            return this.userRepository.updateStatus(user);
        }

        return false;
    }
    */
}
