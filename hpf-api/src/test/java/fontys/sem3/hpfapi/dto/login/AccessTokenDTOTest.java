package fontys.sem3.hpfapi.dto.login;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AccessTokenDTOTest {
    @Test
    void shouldReturnFalseWhenRoleNotFound() {
        AccessTokenDTO token = AccessTokenDTO.builder()
                .subject("admin@hpf.com")
                .roles(null)
                .userId(1L)
                .build();

        assertFalse(token.hasRole("ADMIN"));
    }

    @Test
    void shouldReturnTrue() {
        AccessTokenDTO token = AccessTokenDTO.builder()
                .subject("admin@hpf.com")
                .roles(List.of("ADMIN"))
                .userId(1L)
                .build();

        assertTrue(token.hasRole("ADMIN"));
    }
}
