package fontys.sem3.hpfapi.controller;

import fontys.sem3.hpfapi.business.analytics.GetAnalyticsUseCase;
import fontys.sem3.hpfapi.dto.analytics.AnalyticsDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class AnalyticsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetAnalyticsUseCase getAnalyticsUseCase;

    @Test
    void getStudent_shouldReturn200WithAnalytics() throws Exception {
        AnalyticsDTO analyticsDTO = AnalyticsDTO.builder()
                .petsAdoptedTotal(1L)
                .customersSatisfiedTotal(1L)
                .donationsReceivedTotal(289.49d)
                .build();
        when(getAnalyticsUseCase.getAnalytics()).thenReturn(analyticsDTO);

        mockMvc.perform(get("/analytics"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {"petsAdoptedTotal":1,"customersSatisfiedTotal":1,"donationsReceivedTotal":289.49}
                        """));

        verify(getAnalyticsUseCase).getAnalytics();
    }
}
