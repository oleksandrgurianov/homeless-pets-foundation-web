package fontys.sem3.hpfapi.dto.customer;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerAddressRequestDTO {
    private Long id;

    private String street;

    private String postcode;

    private String city;
}
