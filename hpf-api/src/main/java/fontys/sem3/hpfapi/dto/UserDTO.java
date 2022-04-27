package fontys.sem3.hpfapi.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    protected Long id;
    protected String avatar;
    protected String fullName;
    protected String email;
    protected String phoneNumber;
    protected String password;
    protected String role;
}
