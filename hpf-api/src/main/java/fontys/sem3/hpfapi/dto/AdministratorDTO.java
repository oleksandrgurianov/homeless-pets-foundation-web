package fontys.sem3.hpfapi.dto;

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
