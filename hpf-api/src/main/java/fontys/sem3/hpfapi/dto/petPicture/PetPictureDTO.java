package fontys.sem3.hpfapi.dto.petPicture;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetPictureDTO {
    private Long id;

    private String picture;
}
