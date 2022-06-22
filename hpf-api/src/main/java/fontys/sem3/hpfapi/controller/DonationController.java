package fontys.sem3.hpfapi.controller;

import fontys.sem3.hpfapi.business.donation.CreateDonationUseCase;
//import fontys.sem3.hpfapi.business.donation.GetDonationUseCase;
import fontys.sem3.hpfapi.business.donation.GetDonationsUseCase;
import fontys.sem3.hpfapi.configuration.security.isauthenticated.IsAuthenticated;
import fontys.sem3.hpfapi.dto.donation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/donations")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
public class DonationController {
    private final CreateDonationUseCase createDonationUseCase;
    private final GetDonationsUseCase getDonationsUseCase;
//    private final GetDonationUseCase getDonationUseCase;

    @PostMapping()
    public ResponseEntity<CreateDonationResponseDTO> createDonation(@RequestBody @Valid CreateDonationRequestDTO request) {
        CreateDonationResponseDTO response = createDonationUseCase.createDonation(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @IsAuthenticated
    @RolesAllowed({"ADMIN"})
    @GetMapping
    public ResponseEntity<GetDonationsResponseDTO> getDonations(@RequestParam(value = "userId", required = false) Long userId) {
        GetDonationsRequestDTO request = new GetDonationsRequestDTO();
        request.setUserId(userId);
        return ResponseEntity.ok(getDonationsUseCase.getDonations(request));
    }

//    @GetMapping("{id}")
//    public ResponseEntity<DonationDTO> getDonation(@PathVariable(value = "id") final long id) {
//        final Optional<DonationDTO> donationOptional = getDonationUseCase.getDonation(id);
//
//        if (donationOptional.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        return ResponseEntity.ok().body(donationOptional.get());
//    }
}
