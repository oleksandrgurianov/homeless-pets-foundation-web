package fontys.sem3.hpfapi.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DonationDTO {
    private Long id;

    private CustomerDTO customer;

    private Double amount;

    private LocalDate dateOfReceipt;

    private String description;
}
