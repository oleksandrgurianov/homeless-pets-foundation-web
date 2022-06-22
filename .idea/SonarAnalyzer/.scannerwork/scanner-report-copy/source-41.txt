package fontys.sem3.hpfapi.repository.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pet_picture")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetPicture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @NotBlank
    @Column(name = "picture")
    private String picture;
}
