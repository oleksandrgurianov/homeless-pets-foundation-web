package fontys.sem3.hpfapi.dto.customer;

import lombok.*;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerBankDetailsRequestDTO {
    private Long userId;

    private String cardNumber;

    private String expirationDate;

    private String cvv;
}
