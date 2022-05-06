package fontys.sem3.hpfapi.dto;

import lombok.*;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetCustomersRequestDTO {
    private String userFullName;

    @NotBlank
    private Boolean status;

    private Boolean ascending;
}
