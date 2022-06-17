package fontys.sem3.hpfapi.business;

import fontys.sem3.hpfapi.dto.AccessTokenDTO;

public interface AccessTokenDecoder {
    AccessTokenDTO decode(String accessTokenEncoded);
}
