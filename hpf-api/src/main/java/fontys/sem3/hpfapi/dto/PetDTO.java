package fontys.sem3.hpfapi.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetDTO {
    private Long id;
    private CustomerDTO customer;
    private String type;
    private String name;
    private String breed;
    private String ageCategory;
    private String gender;
    private String size;
    private String color;
    private String description;
    private Double adoptionFee;
}
