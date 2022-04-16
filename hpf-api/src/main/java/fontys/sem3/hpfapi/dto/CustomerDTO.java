package fontys.sem3.hpfapi.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class CustomerDTO extends UserDTO {
    private String street;
    private String postcode;
    private String city;
    private String cardNumber;
    private String expirationDate;
    private String cvv;
    private boolean status;

    public CustomerDTO(int id, String avatar, String firstName, String lastName, String phoneNumber, String email, String password, String role, String street, String postcode, String city, String cardNumber, String expirationDate, String cvv, boolean status) {
        super(id, avatar, firstName, lastName, phoneNumber, email, password, role);
        this.street = street;
        this.postcode = postcode;
        this.city = city;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.status = status;
    }
}
