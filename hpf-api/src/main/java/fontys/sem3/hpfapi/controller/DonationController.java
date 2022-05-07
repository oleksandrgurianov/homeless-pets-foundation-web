package fontys.sem3.hpfapi.controller;

import fontys.sem3.hpfapi.business.CreateDonationUseCase;
import fontys.sem3.hpfapi.business.GetDonationUseCase;
import fontys.sem3.hpfapi.business.GetDonationsUseCase;
import fontys.sem3.hpfapi.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/donations")
@RequiredArgsConstructor
public class DonationController {
    private final CreateDonationUseCase createDonationUseCase;
    private final GetDonationsUseCase getDonationsUseCase;
    private final GetDonationUseCase getDonationUseCase;

    @PostMapping()
    public ResponseEntity<CreateDonationResponseDTO> createDonation(@RequestBody @Valid CreateDonationRequestDTO request) {
        CreateDonationResponseDTO response = createDonationUseCase.createDonation(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<GetDonationsResponseDTO> getDonations(@RequestParam(value = "customerId", required = false) Long customerId, @RequestParam(value = "startDate", required = false) Date startDate, @RequestParam(value = "endDate", required = false) Date endDate, @RequestParam(value = "orderByDateOfReceipt", required = false) Boolean orderByDateOfReceipt, @RequestParam(value = "ascending", required = false) Boolean ascending) {
        GetDonationsRequestDTO request = new GetDonationsRequestDTO();
        request.setCustomerId(customerId);
        request.setStartDate(startDate);
        request.setEndDate(endDate);
        request.setOrderByDateOfReceipt(orderByDateOfReceipt);
        request.setAscending(ascending);
        return ResponseEntity.ok(getDonationsUseCase.getDonations(request));
    }

    @GetMapping("{id}")
    public ResponseEntity<DonationDTO> getDonation(@PathVariable(value = "id") final long id) {
        final Optional<DonationDTO> donationOptional = getDonationUseCase.getDonation(id);

        if (donationOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(donationOptional.get());
    }
}
