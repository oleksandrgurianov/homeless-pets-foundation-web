package fontys.sem3.hpfapi.dto;

import lombok.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DonationDTO {
    private int id;
    private int customerId;
    private double amount;
    private Date dateOfReceipt;
    private String description;
}
