package fontys.sem3.hpfapi.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetPictureDTO {
    private Long id;
    private PetDTO pet;
    private String picture;
}
