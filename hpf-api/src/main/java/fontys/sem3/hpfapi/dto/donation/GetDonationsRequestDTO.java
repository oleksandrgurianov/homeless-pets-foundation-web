package fontys.sem3.hpfapi.dto.donation;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetDonationsRequestDTO {
    private Long userId;
}
