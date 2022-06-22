package fontys.sem3.hpfapi.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetPetsRequestDTO {
    private String type;

    private String breed;

    private Long customerId;

    private Boolean ascending;
}
