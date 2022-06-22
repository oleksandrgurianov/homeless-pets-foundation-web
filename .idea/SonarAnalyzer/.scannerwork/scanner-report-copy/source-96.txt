package fontys.sem3.hpfapi.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyExistsException extends ResponseStatusException {
    public UserAlreadyExistsException() {
        super(HttpStatus.BAD_REQUEST, "USER_ALREADY_EXISTS");
    }
}
