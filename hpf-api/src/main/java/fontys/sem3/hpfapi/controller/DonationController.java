package fontys.sem3.hpfapi.controller;

import fontys.sem3.hpfapi.business.donation.CreateDonationUseCase;
import fontys.sem3.hpfapi.business.donation.GetDonationsUseCase;
import fontys.sem3.hpfapi.configuration.security.isauthenticated.IsAuthenticated;
import fontys.sem3.hpfapi.dto.donation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequestMapping("/donations")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
public class DonationController {
    private final CreateDonationUseCase createDonationUseCase;
    private final GetDonationsUseCase getDonationsUseCase;

    @PostMapping()
    public ResponseEntity<CreateDonationResponseDTO> createDonation(@RequestBody @Valid CreateDonationRequestDTO request) {
        CreateDonationResponseDTO response = createDonationUseCase.createDonation(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @IsAuthenticated
    @RolesAllowed({"ADMIN"})
    @GetMapping
    public ResponseEntity<GetDonationsResponseDTO> getDonations() {
        return ResponseEntity.ok(getDonationsUseCase.getDonations());
    }
}
