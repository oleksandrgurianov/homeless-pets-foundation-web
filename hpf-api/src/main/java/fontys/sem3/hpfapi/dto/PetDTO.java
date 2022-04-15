package fontys.sem3.hpfapi.dto;

import lombok.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetDTO {
    private int id;
    private List<String> pictures;
    private String type;
    private String name;
    private String breed;
    private String ageCategory;
    private String gender;
    private String size;
    private String color;
    private String description;
    private double adoptionFee;
}
