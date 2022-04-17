package fontys.sem3.hpfapi.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class AdministratorDTO extends UserDTO {
    private String jobPosition;

    public AdministratorDTO(int id, String avatar, String fullName, String phoneNumber, String email, String password, String role, String jobPosition) {
        super(id, avatar, fullName, phoneNumber, email, password, role);
        this.jobPosition = jobPosition;
    }
}
