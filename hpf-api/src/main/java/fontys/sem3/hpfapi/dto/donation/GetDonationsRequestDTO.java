package fontys.sem3.hpfapi.dto.donation;

import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetDonationsRequestDTO {
    private Long customerId;

    private Date startDate;

    private Date endDate;

    private Boolean orderByDateOfReceipt;

    private Boolean ascending;
}
