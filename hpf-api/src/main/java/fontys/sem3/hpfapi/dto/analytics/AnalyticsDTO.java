package fontys.sem3.hpfapi.dto.analytics;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnalyticsDTO {
    private Long petsAdoptedTotal;

    private Long customersSatisfiedTotal;

    private Double donationsReceivedTotal;
}
