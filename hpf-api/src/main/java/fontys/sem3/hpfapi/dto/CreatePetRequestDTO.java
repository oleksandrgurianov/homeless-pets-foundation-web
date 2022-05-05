package fontys.sem3.hpfapi.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePetRequestDTO {
    @NotBlank
    private String type;

    @NotBlank
    private String name;

    @NotBlank
    private String breed;

    private String ageCategory;

    private String gender;

    private String size;

    private String color;

    private String description;

    private Double adoptionFee;
}
