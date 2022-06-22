package fontys.sem3.hpfapi.dto;

import lombok.*;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateDonationRequestDTO {
    private Long customerId;

    @NotNull
    private Double amount;

    private String description;
}
