package fontys.sem3.hpfapi.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetPetPicturesResponseDTO {
    List<PetPictureDTO> petPictures;
}
