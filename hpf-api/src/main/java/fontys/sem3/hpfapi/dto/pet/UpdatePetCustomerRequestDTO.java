package fontys.sem3.hpfapi.dto.pet;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePetCustomerRequestDTO {
    private Long id;

    @NotNull
    private Long customerId;
}
