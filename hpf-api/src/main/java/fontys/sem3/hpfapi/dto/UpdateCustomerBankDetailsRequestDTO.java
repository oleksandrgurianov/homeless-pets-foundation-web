package fontys.sem3.hpfapi.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerBankDetailsRequestDTO {
    private Long id;

    private String cardNumber;

    private String expirationDate;

    private String cvv;
}
