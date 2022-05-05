package fontys.sem3.hpfapi.dto;

import lombok.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePetPictureRequestDTO {
    @NotNull
    private Long petId;

    @NotBlank
    private String picture;
}
