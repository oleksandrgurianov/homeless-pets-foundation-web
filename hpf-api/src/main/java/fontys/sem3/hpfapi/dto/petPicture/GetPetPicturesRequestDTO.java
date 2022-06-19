package fontys.sem3.hpfapi.dto.petPicture;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetPetPicturesRequestDTO {
    @NotNull
    private Long petId;
}
