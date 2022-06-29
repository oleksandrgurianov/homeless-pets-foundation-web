package fontys.sem3.hpfapi.controller;

import fontys.sem3.hpfapi.business.customer.GetCustomerUseCase;
import fontys.sem3.hpfapi.business.customer.UpdateCustomerUseCase;
import fontys.sem3.hpfapi.configuration.security.isauthenticated.IsAuthenticated;
import fontys.sem3.hpfapi.dto.customer.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
public class CustomerController {
    private final GetCustomerUseCase getCustomerUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;

    @IsAuthenticated
    @RolesAllowed({"CUST"})
    @GetMapping("{userId}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable(value = "userId") final long userId) {
        final Optional<CustomerDTO> customerOptional = getCustomerUseCase.getCustomer(userId);

        if (customerOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(customerOptional.get());
    }

    @IsAuthenticated
    @RolesAllowed({"CUST"})
    @PutMapping("{userId}/address")
    public ResponseEntity<CustomerDTO> updateCustomerAddress(@PathVariable("userId") long userId, @RequestBody @Valid UpdateCustomerAddressRequestDTO request) {
        request.setUserId(userId);
        updateCustomerUseCase.updateCustomerAddress(request);
        return ResponseEntity.noContent().build();
    }

    @IsAuthenticated
    @RolesAllowed({"CUST"})
    @PutMapping("{userId}/bankDetails")
    public ResponseEntity<CustomerDTO> updateCustomerBankDetails(@PathVariable("userId") long userId, @RequestBody @Valid UpdateCustomerBankDetailsRequestDTO request) {
        request.setUserId(userId);
        updateCustomerUseCase.updateCustomerBankDetails(request);
        return ResponseEntity.noContent().build();
    }
}
