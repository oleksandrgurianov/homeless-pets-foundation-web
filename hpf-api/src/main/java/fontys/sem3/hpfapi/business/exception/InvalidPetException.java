package fontys.sem3.hpfapi.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidPetException extends ResponseStatusException {
    public InvalidPetException() {
        super(HttpStatus.BAD_REQUEST, "PET_INVALID");
    }

    public InvalidPetException(String errorCode) {
        super(HttpStatus.BAD_REQUEST, errorCode);
    }
}
