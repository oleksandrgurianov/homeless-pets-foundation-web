package fontys.sem3.hpfapi.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    protected int id;
    protected String avatar;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String phoneNumber;
    protected String password;
    protected String role;

    public UserDTO(int id, String avatar, String firstName, String lastName, String phoneNumber, String email, String password, String role) {
        this.id = id;
        this.avatar = avatar;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
