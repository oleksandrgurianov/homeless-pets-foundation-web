package fontys.sem3.hpfapi.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;

    private String avatar;

    private String fullName;

    private String email;

    private String phoneNumber;

    private String password;

    private List<String> roles;
}
