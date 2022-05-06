package fontys.sem3.hpfapi.dto;

import lombok.*;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserPasswordRequestDTO {
    private Long id;

    @NotBlank
    private String password;
}
