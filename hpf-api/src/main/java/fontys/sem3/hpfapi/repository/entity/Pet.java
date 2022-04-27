package fontys.sem3.hpfapi.repository.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "pet")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "pet")
    private List<PetPicture> pictures;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @NotBlank
    @Column(name = "type")
    private String type;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "breed")
    private String breed;

    @Column(name = "ageCategory")
    private String ageCategory;

    @Column(name = "gender")
    private String gender;

    @Column(name = "size")
    private String size;

    @Column(name = "color")
    private String color;

    @Column(name = "description")
    private String description;

    @Column(name = "adoptionFee")
    private Double adoptionFee;
}
