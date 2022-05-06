package fontys.sem3.hpfapi.dto;

import lombok.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetPetPicturesResponseDTO {
    private List<PetPictureDTO> petPictures;
}
