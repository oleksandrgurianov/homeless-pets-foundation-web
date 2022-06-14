package fontys.sem3.hpfapi.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PetAlreadyExistsException extends ResponseStatusException {
    public PetAlreadyExistsException() {
        super(HttpStatus.BAD_REQUEST, "PET_ALREADY_EXISTS");
    }
}
