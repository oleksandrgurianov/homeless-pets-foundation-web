package fontys.sem3.hpfapi.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserAvatarRequestDTO {
    private Long id;

    private String avatar;
}
