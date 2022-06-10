package fontys.sem3.hpfapi.controller;

import fontys.sem3.hpfapi.business.CreateCustomerUseCase;
import fontys.sem3.hpfapi.business.GetCustomerUseCase;
import fontys.sem3.hpfapi.business.UpdateCustomerUseCase;
import fontys.sem3.hpfapi.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
public class CustomerController {
    private final CreateCustomerUseCase createCustomerUseCase;
    private final GetCustomerUseCase getCustomerUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;

    @PostMapping()
    public ResponseEntity<CreateCustomerResponseDTO> createCustomer(@RequestBody @Valid CreateCustomerRequestDTO request) {
        CreateCustomerResponseDTO response = createCustomerUseCase.createCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable(value = "id") final long id) {
        final Optional<CustomerDTO> customerOptional = getCustomerUseCase.getCustomer(id);

        if (customerOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(customerOptional.get());
    }

    @PutMapping("{id}/address")
    public ResponseEntity<CustomerDTO> updateCustomerAddress(@PathVariable("id") long id, @RequestBody @Valid UpdateCustomerAddressRequestDTO request) {
        request.setId(id);
        updateCustomerUseCase.updateCustomerAddress(request);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}/bankDetails")
    public ResponseEntity<CustomerDTO> updateCustomerBankDetails(@PathVariable("id") long id, @RequestBody @Valid UpdateCustomerBankDetailsRequestDTO request) {
        request.setId(id);
        updateCustomerUseCase.updateCustomerBankDetails(request);
        return ResponseEntity.noContent().build();
    }
}
