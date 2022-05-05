package fontys.sem3.hpfapi.dto;

import lombok.*;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequestDTO {
    private String avatar;

    @NotBlank
    private String fullName;

    @NotBlank
    private String email;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String password;

    @NotBlank
    private String role;
}
