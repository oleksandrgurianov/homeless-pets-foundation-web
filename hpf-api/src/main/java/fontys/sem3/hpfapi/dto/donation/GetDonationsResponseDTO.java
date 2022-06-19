package fontys.sem3.hpfapi.dto.donation;

import fontys.sem3.hpfapi.dto.donation.DonationDTO;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetDonationsResponseDTO {
    private List<DonationDTO> donations;
}
