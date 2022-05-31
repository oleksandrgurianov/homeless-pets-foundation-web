package fontys.sem3.hpfapi.repository.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "donation")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @NonNull
    @Column(name = "amount")
    private Double amount;

    @NonNull
    @Column(name = "date_of_receipt")
    private Date dateOfReceipt;

    @Column(name = "description")
    private String description;
}
