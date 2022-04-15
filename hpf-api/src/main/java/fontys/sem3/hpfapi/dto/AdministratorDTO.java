package fontys.sem3.hpfapi.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdministratorDTO extends UserDTO {
    private String jobPosition;
}
