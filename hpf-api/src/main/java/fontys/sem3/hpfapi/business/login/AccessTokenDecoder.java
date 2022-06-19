package fontys.sem3.hpfapi.business.login;

import fontys.sem3.hpfapi.dto.login.AccessTokenDTO;

public interface AccessTokenDecoder {
    AccessTokenDTO decode(String accessTokenEncoded);
}
