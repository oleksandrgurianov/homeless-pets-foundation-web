package fontys.sem3.hpfapi.dto.customer;

import fontys.sem3.hpfapi.dto.user.UserDTO;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private Long id;

    private UserDTO user;

    private String street;

    private String postcode;

    private String city;

    private String cardNumber;

    private String expirationDate;

    private String cvv;
}
