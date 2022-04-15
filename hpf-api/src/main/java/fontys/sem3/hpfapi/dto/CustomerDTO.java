package fontys.sem3.hpfapi.dto;

import lombok.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO extends UserDTO {
    private String street;
    private String postcode;
    private String city;
    private Long cardNumber;
    private Date expirationDate;
    private int cvv;
    private boolean status;
}
