package fontys.sem3.hpfapi.dto;

import lombok.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DonationDTO {
    private int id;
    private CustomerDTO customerDTO;
    private double amount;
    private Date dateOfReceipt;
    private String description;
}
