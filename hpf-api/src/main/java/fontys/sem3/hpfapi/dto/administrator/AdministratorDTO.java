package fontys.sem3.hpfapi.dto.administrator;

import fontys.sem3.hpfapi.dto.user.UserDTO;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdministratorDTO {
    private Long id;

    private UserDTO user;

    private String jobPosition;
}
