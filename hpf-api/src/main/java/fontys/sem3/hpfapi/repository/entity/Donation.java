package fontys.sem3.hpfapi.repository.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

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

    @NotNull
    @Column(name = "amount")
    private Double amount;

    @NotNull
    @Column(name = "date_of_receipt")
    private LocalDate dateOfReceipt;

    @Column(name = "description")
    private String description;
}
