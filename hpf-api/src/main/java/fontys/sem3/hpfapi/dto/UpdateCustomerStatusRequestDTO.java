package fontys.sem3.hpfapi.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerStatusRequestDTO {
    private Long id;

    private Boolean status;
}
