package fontys.sem3.hpfapi.dto.pet;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetPetsRequestDTO {
    private Long customerId;

    @NotBlank
    private String type;
}
