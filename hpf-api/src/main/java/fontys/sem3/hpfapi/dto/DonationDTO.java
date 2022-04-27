package fontys.sem3.hpfapi.dto;

import lombok.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DonationDTO {
    private Long id;
    private CustomerDTO customer;
    private Double amount;
    private Date dateOfReceipt;
    private String description;
}
