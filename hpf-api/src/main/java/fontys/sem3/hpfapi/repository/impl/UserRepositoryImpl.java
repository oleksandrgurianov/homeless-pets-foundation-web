package fontys.sem3.hpfapi.repository.impl;

import fontys.sem3.hpfapi.dto.UserDTO;
import fontys.sem3.hpfapi.repository.TemporaryDatabase;
import fontys.sem3.hpfapi.repository.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;

import static java.util.Collections.sort;

@Primary
@Service
public class UserRepositoryImpl implements UserRepository {
    private final TemporaryDatabase temporaryDatabase = new TemporaryDatabase();

    @Override
    public UserDTO getUserByEmailAndPassword(String email, String password) {
        for (UserDTO u : temporaryDatabase.usersList) {
            if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
                return u;
            }
        }

        return null;
    }

    @Override
    public UserDTO getUserById(int id) {
        for (UserDTO u : temporaryDatabase.usersList) {
            if (u.getId() == id) {
                return u;
            }
        }

        return null;
    }

    @Override
    public ArrayList<UserDTO> getCustomersBySearch(String search) {
        ArrayList<UserDTO> users = new ArrayList<>();

        for (UserDTO u : temporaryDatabase.usersList) {
            if (u.getFullName().contains(search)) {
                users.add(u);
            }
        }

        return users;
    }

    @Override
    public ArrayList<UserDTO> getCustomersSortedByFullName(boolean ascending) {
        ArrayList<UserDTO> users = new ArrayList<>();

        for (UserDTO u : temporaryDatabase.usersList) {
            if (u.getRole().equals("CUSTOMER")) {
                users.add(u);
            }
        }

        Comparator<UserDTO> compareByFullName =
                Comparator.comparing(UserDTO::getFullName);

        if (ascending) {
            sort(users, compareByFullName);
        } else {
            sort(users, compareByFullName.reversed());
        }

        return users;
    }
}
