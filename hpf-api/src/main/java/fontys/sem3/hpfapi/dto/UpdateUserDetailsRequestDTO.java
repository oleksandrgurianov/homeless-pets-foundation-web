package fontys.sem3.hpfapi.dto;

import lombok.*;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDetailsRequestDTO {
    private Long id;

    @NotBlank
    private String fullName;

    @NotBlank
    private String email;

    @NotBlank
    private String phoneNumber;
}
