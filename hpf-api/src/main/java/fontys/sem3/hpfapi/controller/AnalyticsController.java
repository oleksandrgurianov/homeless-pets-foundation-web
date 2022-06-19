package fontys.sem3.hpfapi.controller;

import fontys.sem3.hpfapi.business.analytics.GetAnalyticsUseCase;
import fontys.sem3.hpfapi.dto.analytics.AnalyticsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analytics")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
public class AnalyticsController {
    private final GetAnalyticsUseCase getAnalyticsUseCase;

    @GetMapping
    public ResponseEntity<AnalyticsDTO> getAnalytics() {
        return ResponseEntity.ok().body(getAnalyticsUseCase.getAnalytics());
    }
}
