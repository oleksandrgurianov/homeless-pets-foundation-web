package fontys.sem3.hpfapi.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerBankDetailsRequestDTO {
    private Long id;

    @Length(min = 16, max = 16)
    private String cardNumber;

    @Length(min = 5, max = 5)
    private String expirationDate;

    @Length(min = 3, max = 3)
    private String cvv;
}
