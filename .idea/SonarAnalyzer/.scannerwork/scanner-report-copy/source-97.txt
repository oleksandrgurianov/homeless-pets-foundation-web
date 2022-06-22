package fontys.sem3.hpfapi.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidCustomerException extends ResponseStatusException {
    public InvalidCustomerException() {
        super(HttpStatus.BAD_REQUEST, "CUSTOMER_INVALID");
    }

    public InvalidCustomerException(String errorCode) {
        super(HttpStatus.BAD_REQUEST, errorCode);
    }
}
