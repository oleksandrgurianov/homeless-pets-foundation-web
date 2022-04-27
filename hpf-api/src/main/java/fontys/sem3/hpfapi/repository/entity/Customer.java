package fontys.sem3.hpfapi.repository.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "customer")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "customer")
    private List<Donation> donations;

    @OneToMany(mappedBy = "customer")
    private List<Pet> pets;

    @Column(name = "street")
    private String street;

    @Column(name = "postcode")
    private String postcode;

    @Column(name = "city")
    private String city;

    @Column(name = "cardNumber")
    private String cardNumber;

    @Column(name = "expirationDate")
    private String expirationDate;

    @Column(name = "cvv")
    private String cvv;

    @Column(name = "status")
    private Boolean status;
}
