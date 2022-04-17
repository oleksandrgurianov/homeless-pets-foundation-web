package fontys.sem3.hpfapi.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    protected int id;
    protected String avatar;
    protected String fullName;
    protected String email;
    protected String phoneNumber;
    protected String password;
    protected String role;
}
