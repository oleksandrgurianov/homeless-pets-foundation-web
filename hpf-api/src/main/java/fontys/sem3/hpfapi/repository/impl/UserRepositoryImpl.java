package fontys.sem3.hpfapi.repository.impl;

import fontys.sem3.hpfapi.dto.CustomerDTO;
import fontys.sem3.hpfapi.dto.UserDTO;
import fontys.sem3.hpfapi.dto.AdministratorDTO;
import fontys.sem3.hpfapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
@Qualifier("users")
@Primary
public class UserRepositoryImpl implements UserRepository {
    @Override
    public List<UserDTO> getUsers() {
        final List<UserDTO> usersList = new ArrayList<>();
        usersList.add(new AdministratorDTO(1, "https://drive.google.com/file/d/1_dkDl3VGEjdKqyO900DmdmBGg3DbaVVo/view?usp=sharing", "Manna", "Barnhoorn", "0641261843", "admin@hpf.com", "password", "ADMIN", "Client Service Representative"));
        usersList.add(new CustomerDTO(2, "https://drive.google.com/file/d/1Rm-dceeyImW5JIBJGBrMiNKUzegRZ_Qo/view?usp=sharing", "Pascal", "Broeks", "0651535133", "cust1@gmail.com", "password", "CUST", "Singel 57", "3311 HP", "Dordrecht", "5561036905645903", "05/26", "677", true));
        usersList.add(new CustomerDTO(3, "https://drive.google.com/file/d/1HZ10iuK40owzVBmqix-YyWz2OVwuBwpD/view?usp=sharing", "Ingemar", "Coumans", "0616816346", "cust2@gmail.com", "password", "CUST", "Parkweg 132", "6511 BG", "Nijmegen", null, null, null, true));
        usersList.add(new CustomerDTO(4, "https://drive.google.com/file/d/1CCXztuaEGv9H95TfMVBuDyDX3ElL4tBc/view?usp=sharing", "Pearl", "Knijnenburg", "0682771024", "cust3@gmail.com", "password", "CUST", "Andre Severinweg 68", "6214 PM", "Maastricht", "5327789050901654", "05/26", "170", false));
        return usersList;
    }
}
