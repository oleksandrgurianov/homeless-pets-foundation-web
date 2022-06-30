package fontys.sem3.hpfapi.business.login;

import fontys.sem3.hpfapi.business.login.impl.AccessTokenEncoderDecoderImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import fontys.sem3.hpfapi.dto.login.AccessTokenDTO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class AccessTokenEncoderDecoderImplTest {
    @InjectMocks
    private AccessTokenEncoderDecoderImpl accessTokenEncoderDecoder = new AccessTokenEncoderDecoderImpl("E91E158E4C6656F68B1B5D1C311766DE98D2AD6EF3BFB33F78E9CFCDF9");

    @Test
    void shouldReturnAccessTokenEncoded() {
        AccessTokenDTO token = AccessTokenDTO.builder()
                .subject("admin@hpf.com")
                .roles(List.of("ADMIN"))
                .userId(1L)
                .build();

        String actualResult = accessTokenEncoderDecoder.encode(token);

        String expectedResult = "eyJhbGciOiJIUzI1NiJ9.";

        assertEquals(expectedResult, actualResult.substring(0, 21));
    }

    @Test
    void shouldReturnAccessTokenEncodedWhenNoUserIdInformed() {
        AccessTokenDTO token = AccessTokenDTO.builder()
                .subject("admin@hpf.com")
                .roles(List.of("ADMIN"))
                .userId(null)
                .build();

        String actualResult = accessTokenEncoderDecoder.encode(token);

        String expectedResult = "eyJhbGciOiJIUzI1NiJ9.";

        assertEquals(expectedResult, actualResult.substring(0, 21));
    }

    @Test
    void shouldReturnAccessTokenEncodedWhenNoRoleInformed() {
        AccessTokenDTO token = AccessTokenDTO.builder()
                .subject("admin@hpf.com")
                .roles(null)
                .userId(1L)
                .build();

        String actualResult = accessTokenEncoderDecoder.encode(token);

        String expectedResult = "eyJhbGciOiJIUzI1NiJ9.";

        assertEquals(expectedResult, actualResult.substring(0, 21));
    }
}