package fontys.sem3.hpfapi.dto;

import lombok.*;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerRequestDTO {
    @NotNull
    private Long userId;

    private String street;

    private String postcode;

    private String city;

    private String cardNumber;

    private String expirationDate;

    private String cvv;
}
