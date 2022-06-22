//package fontys.sem3.hpfapi.dto.customer;
//
//import lombok.*;
//import org.hibernate.validator.constraints.Length;
//
//import javax.validation.constraints.NotNull;
//
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//public class CreateCustomerRequestDTO {
//    @NotNull
//    private Long userId;
//
//    private String street;
//
//    private String postcode;
//
//    private String city;
//
//    @Length(min = 16, max = 16)
//    private String cardNumber;
//
//    @Length(min = 5, max = 5)
//    private String expirationDate;
//
//    @Length(min = 3, max = 3)
//    private String cvv;
//}
