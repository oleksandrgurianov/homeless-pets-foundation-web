package fontys.sem3.hpfapi.business;

import fontys.sem3.hpfapi.dto.AccessTokenDTO;

public interface AccessTokenEncoder {
    String encode(AccessTokenDTO accessTokenDTO);
}
