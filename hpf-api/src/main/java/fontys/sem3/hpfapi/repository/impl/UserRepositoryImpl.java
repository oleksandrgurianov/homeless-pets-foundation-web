package fontys.sem3.hpfapi.repository.impl;

import fontys.sem3.hpfapi.dto.CustomerDTO;
import fontys.sem3.hpfapi.dto.UserDTO;
import fontys.sem3.hpfapi.repository.TemporaryDatabase;
import fontys.sem3.hpfapi.repository.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Comparator;

@Primary
@Service
public class UserRepositoryImpl implements UserRepository {
    private final TemporaryDatabase temporaryDatabase = new TemporaryDatabase();

    @Override
    public UserDTO getUserByEmailAndPassword(String email, String password) {
        for (UserDTO u : temporaryDatabase.usersList) {
            if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
                if (u.getRole().equals("CUSTOMER") && u instanceof CustomerDTO) {
                    if (!((CustomerDTO) u).isStatus()) {
                        return null;
                    }
                }

                return u;
            }
        }

        return null;
    }

    @Override
    public ArrayList<UserDTO> getSortedCustomersBySearchAndStatus(String search, boolean ascending, boolean approved) {
        ArrayList<UserDTO> users = new ArrayList<>();

        for (UserDTO u : temporaryDatabase.usersList) {
            if (u.getRole().equals("CUSTOMER") && u instanceof CustomerDTO) {
                if (approved == ((CustomerDTO) u).isStatus()) {
                    if (!search.isBlank()) {
                        if (u.getFullName().contains(search)) {
                            users.add(u);
                        }
                    } else {
                        users.add(u);
                    }
                }
            }
        }

        Comparator<UserDTO> compareByFullName =
                Comparator.comparing(UserDTO::getFullName);

        if (!users.isEmpty()) {
            if (ascending) {
                users.sort(compareByFullName);
            } else {
                users.sort(compareByFullName.reversed());
            }
        }

        return users;
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
    public boolean userExists(UserDTO user) {
        for (UserDTO u : temporaryDatabase.usersList) {
            if (u.getEmail().equals(user.getEmail())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void createUser(UserDTO user) {
        this.temporaryDatabase.usersList.add(user);
    }

    @Override
    public boolean updateAvatar(UserDTO user) {
        for (UserDTO u : temporaryDatabase.usersList) {
            if (u.getId() == user.getId()) {
                if (u.getRole().equals("CUSTOMER") && u instanceof CustomerDTO) {
                    if (!((CustomerDTO) u).isStatus()) {
                        return false;
                    }
                }

                u.setAvatar(user.getAvatar());
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean updateUser(UserDTO user) {
        for (UserDTO u : temporaryDatabase.usersList) {
            if (u.getId() == user.getId()) {
                if (u.getRole().equals("CUSTOMER") && u instanceof CustomerDTO) {
                    if (!((CustomerDTO) u).isStatus()) {
                        return false;
                    }

                    ((CustomerDTO) u).setStreet(((CustomerDTO) user).getStreet());
                    ((CustomerDTO) u).setPostcode(((CustomerDTO) user).getPostcode());
                    ((CustomerDTO) u).setCity(((CustomerDTO) user).getCity());
                }

                u.setFullName(user.getFullName());
                u.setEmail(user.getEmail());
                u.setPhoneNumber(user.getPhoneNumber());
                u.setPassword(user.getPassword());

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean updateCard(UserDTO user) {
        for (UserDTO u : temporaryDatabase.usersList) {
            if (u.getId() == user.getId()) {
                if (u.getRole().equals("CUSTOMER") && u instanceof CustomerDTO) {
                    if (!((CustomerDTO) u).isStatus()) {
                        return false;
                    }

                    ((CustomerDTO) u).setCardNumber(((CustomerDTO) user).getCardNumber());
                    ((CustomerDTO) u).setExpirationDate(((CustomerDTO) user).getExpirationDate());
                    ((CustomerDTO) u).setCvv(((CustomerDTO) user).getCvv());
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean deleteAvatar(UserDTO user) {
        if (!user.getAvatar().isBlank()) {
            for (UserDTO u : temporaryDatabase.usersList) {
                if (u.getId() == user.getId()) {
                    if (u.getRole().equals("CUSTOMER") && u instanceof CustomerDTO) {
                        if (!((CustomerDTO) u).isStatus()) {
                            return false;
                        }
                    }

                    u.setAvatar(null);
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean deleteCustomer(UserDTO user) {
        for (UserDTO u : temporaryDatabase.usersList) {
            if (u.getId() == user.getId()) {
                this.temporaryDatabase.usersList.remove(u);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean deleteCard(UserDTO user) {
        for (UserDTO u : temporaryDatabase.usersList) {
            if (u.getId() == user.getId()) {
                if (u.getRole().equals("CUSTOMER") && u instanceof CustomerDTO) {
                    if (!((CustomerDTO) u).isStatus()) {
                        return false;
                    }

                    ((CustomerDTO) u).setCardNumber(null);
                    ((CustomerDTO) u).setExpirationDate(null);
                    ((CustomerDTO) u).setCvv(null);
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean updateStatus(UserDTO user) {
        for (UserDTO u : temporaryDatabase.usersList) {
            if (u.getId() == user.getId()) {
                if (u.getRole().equals("CUSTOMER") && u instanceof CustomerDTO) {
                    ((CustomerDTO) u).setStatus(!((CustomerDTO)u).isStatus());
                    return true;
                }
            }
        }

        return false;
    }
}
