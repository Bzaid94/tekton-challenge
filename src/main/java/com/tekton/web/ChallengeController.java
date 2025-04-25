package com.tekton.web;

import com.tekton.bean.CalculationRequest;
import com.tekton.bean.ResponseWS;
import com.tekton.config.doc.CalculateResponses;
import com.tekton.config.doc.HistoryResponses;
import com.tekton.service.CalculationService;
import com.tekton.service.CallHistoryService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/execute")
@RequiredArgsConstructor
public class ChallengeController {

    private final CalculationService calculationService;
    private final CallHistoryService historyService;

    @PostMapping("/calculate")
    @CalculateResponses
    public ResponseEntity<ResponseWS> calculate(@RequestBody CalculationRequest request, @RequestHeader(name = "fail-simulate", defaultValue = "false") boolean simulateFailure) {
        return ResponseEntity.ok(calculationService.calculate(request, simulateFailure));
    }

    @GetMapping("/history")
    @HistoryResponses
    public ResponseEntity<ResponseWS> history(Pageable pageable) {
        ResponseWS response = historyService.getHistory(pageable);
        HttpStatus status = response.getCode() == 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND;

        return ResponseEntity.status(status).body(response);
    }
}
